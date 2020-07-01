package com.yadu.json

import java.time.format.DateTimeFormatter
import java.time.{LocalDate, LocalDateTime}

import org.json4s.JsonAST.{JNull, JString}
import org.json4s.jackson.Serialization._
import org.json4s.{CustomSerializer, DefaultFormats, FieldSerializer, Formats}

/**
 * Created by yadu on 01/07/20
 */


trait JsonParser {

  implicit lazy val serializationFormats: Formats = new DefaultFormats {
    override val fieldSerializers: List[(Class[_], FieldSerializer[_])] = List((classOf[BaseEntity], FieldSerializer[BaseEntity]()))
  } ++ customSerializers

  def fromJson[T](json: String)(implicit mf: Manifest[T]) = {
    read[T](json)
  }

  def toJson(obj: AnyRef): String = {
    write(obj)
  }

  val customSerializers = Seq(
    CustomSerializers.LocalDateSerializer, CustomSerializers.LocalDateTimeSerializer, CustomSerializers.PeriodSerializer
  )

}

object CustomSerializers {

  private val dateFormatter     = DateTimeFormatter.ofPattern("dd-MM-yyyy")
  private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

  case object LocalDateSerializer extends CustomSerializer[LocalDate](format => ( {
    case JString(date) => LocalDate.parse(date, dateFormatter)
    case JNull         => null
  }, {
    case date: LocalDate => JString(date.format(dateFormatter))
  }))

  case object LocalDateTimeSerializer extends CustomSerializer[LocalDateTime](format => ( {
    case JString(dt) => LocalDateTime.parse(dt, dateTimeFormatter)
    case JNull       => null
  }, {
    case dt: LocalDateTime => JString(dt.format(dateTimeFormatter))
  }))

  case object PeriodSerializer extends CustomSerializer[Period](format => ( {
    case JString(dt) => {
      dt match {
        case "Day"   => Period.Day
        case "Week"  => Period.Week
        case "Month" => Period.Month
      }
    }
    case JNull       => null
  }, {
    case p: Period => JString(p.name)
  }))

}