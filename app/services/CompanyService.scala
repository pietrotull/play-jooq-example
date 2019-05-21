package services

import generated.Tables.COMPANY
import org.jooq.DSLContext

import controllers.CompanyForm
import models.Company
import collection.JavaConverters._

import generated.tables.records.CompanyRecord

class CompanyService {
	val dbContext: DSLContext = Driver.getDbContext

	def delCompany(id: Int): Int = {
		val com = getById(id)
		com.delete()
	}

	def storeCompany(data: CompanyForm.Data): Company = {
		val comp = if (data.id.isDefined) {
			val com = getById(data.id.get)
			com.setName(data.name)
			com.setAddress(data.address)
			com.store()
			com
		} else {
			val r = dbContext.newRecord(COMPANY)
			r.setName(data.name)
			r.setAddress(data.address)
			r.store()
			r
		}
		new Company(comp)
	}

	def getAllCompanies: Seq[Company] = dbContext.fetch(COMPANY).asScala.map(new Company(_))

	def getCompanyById(id: Int): Company = new Company(getById(id))

	private def getById(id: Int): CompanyRecord = dbContext.fetchOne(COMPANY, COMPANY.ID.eq(id))
}
