package com.yadu.json

import java.time.LocalDate

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

/**
 * Created by yadu on 01/07/20
 */


class JsonParserSpec extends AnyWordSpec with Matchers {

  val jsonParser = new JsonParser {}

  "Json parser" should {

    "parse Employee case class successfully" in {
      val employeeJson =
        """
          | {
          | "employeeId" : 100,
          | "name": "Yadu",
          | "dob" : "01-01-1988"
          | }
          |""".stripMargin

      val emp = jsonParser.fromJson[Employee](employeeJson)
      emp.name shouldBe "Yadu"
      emp.employeeId shouldBe 100
      emp.dob shouldBe LocalDate.parse("1988-01-01")
    }

    "parse the json and convert period into case object" in {
      val salesQueryJson =
        """
          | {
          | "period" : "Day",
          | "itemCode" : "BK1C"
          | }
          |""".stripMargin

      val salesQuery = jsonParser.fromJson[SalesQuery](salesQueryJson)

      salesQuery.itemCode shouldBe "BK1C"
      salesQuery.period shouldBe Period.Day
    }

    "convert Period object to json correctly" in {
      val salesQuery = SalesQuery(Period.Week, "Q23")
      val json       = jsonParser.toJson(salesQuery)
      json should include("Week")
      //parse again
      val secondParse = jsonParser.fromJson[SalesQuery](json)
      secondParse.period shouldBe Period.Week
    }

    "include the field desc from BaseTrait in Json" in {
      val item = Item("Q23", 13, LocalDate.parse("2020-01-25"))
      val json = jsonParser.toJson(item)
      json should include("Q23")
      json should include("Q23:13")
      println(json)
      //parse again
      val secondParse = jsonParser.fromJson[Item](json)
      secondParse.desc shouldBe "Q23:13"
    }
  }

}
