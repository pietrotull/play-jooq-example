package services

import generated.Tables.CUSTOMER
import org.jooq.DSLContext
import collection.JavaConversions._

import controllers.CustomerForm
import generated.tables.records.CustomerRecord
import models.Customer

class CustomerService {

  val dbContext: DSLContext = Driver.getDbContext

  def getAllCustomers: Seq[Customer] = dbContext.fetch(CUSTOMER).map(new Customer(_))

  def storeCustomer(data: CustomerForm.Data): Customer = {
    val customer = if (data.id.isDefined) {
      val cus = getById(data.id.get)
      cus.setSalary(data.salary.bigDecimal)
      cus.setAge(data.age)
      cus.setName(data.name)
      cus
    } else {
      val r = dbContext.newRecord(CUSTOMER)
      r.setName(data.name)
      r.setAge(data.age)
      r.setAddress(data.address)
      r.setSalary(data.salary.bigDecimal)
      r
    }
    customer.store()
    new Customer(customer)
  }

  def getCustomerById(id: Int): Customer = new Customer(getById(id))

  def delCustomer(id: Int): Int = getById(id).delete()

  private def getById(id: Int): CustomerRecord = dbContext.fetchOne(CUSTOMER, CUSTOMER.ID.eq(id))
}
