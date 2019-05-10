package models

import generated.tables.records.CompanyRecord

case class Company(id: Int, name: String, address: String) {
	def this(cr: CompanyRecord) = this(cr.getId, cr.getName, cr.getAddress)
}
