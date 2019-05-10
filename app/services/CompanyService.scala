package services

import generated.Tables.COMPANY
import org.jooq.DSLContext
import collection.JavaConversions._
import controllers.CompanyForm
import models.Company

class CompanyService {
	val dbContext: DSLContext = Driver.getDbContext()

	def storeCompany(data: CompanyForm.Data): Company = {
		val r = dbContext.newRecord(COMPANY)
		r.setName(data.name)
		r.setAddress(data.address)
		r.store()
		new Company(r)
	}

	def getAllCompanies(): Seq[Company] = dbContext.fetch(COMPANY).map(new Company(_))
}
