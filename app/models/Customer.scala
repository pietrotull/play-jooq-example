package models

import generated.tables.records.CustomerRecord

case class Customer (id: Int, name: String, age: Int, address: String, salary: BigDecimal) {
  def this(cr: CustomerRecord) = this(cr.getId, cr.getName, cr.getAge, cr.getAddress, cr.getSalary)
}