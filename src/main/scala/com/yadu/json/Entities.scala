package com.yadu.json

import java.time.LocalDate

case class Employee(employeeId: Long, name: String, dob: LocalDate) extends BaseEntity {
  override val desc: String = s"$employeeId - $name"
}

case class Item(itemCode: String, qty: Int, purchaseDate: LocalDate) extends BaseEntity {
  override val desc: String = itemCode + ":" + qty
}

case class SalesQuery(period: Period, itemCode: String)

trait BaseEntity {
  def desc: String
}