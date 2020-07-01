package com.yadu.json

/**
 * Created by yadu on 01/07/20
 */


sealed trait Period {
  def id: Int
  def name: String
}

object Period {

  case object Day extends Period {
    override lazy val id = 1
    override lazy val name: String = "Day"
  }

  case object Week extends Period {
    override lazy val id = 2
    override lazy val name: String = "Week"
  }

  case object Month extends Period {
    override lazy val id = 3
    override lazy val name: String = "Month"
  }

}
