package services

import generated.Tables.COMPANY
import org.jooq.{DSLContext}
import collection.JavaConversions._
import collection.JavaConverters._

import generated.tables.records.CompanyRecord

class CompanyService {

	val dbContext: DSLContext = Driver.getDbContext()

	def getAllCompanies(): List[CompanyRecord] = {
		queryCompanyPojos()
	}

	def queryCompanyPojos() = dbContext.fetch(COMPANY).toList
}
