package services

import generated.Tables.CUSTOMER
import org.jooq.DSLContext
import collection.JavaConversions._
import controllers.CustomerForm
import models.Customer

class CustomerService {

  val dbContext: DSLContext = Driver.getDbContext()

  def getAllCustomers(): Seq[Customer] = dbContext.fetch(CUSTOMER).map(new Customer(_))

  def storeCustomer(data: CustomerForm.Data): Customer = {
    val r = dbContext.newRecord(CUSTOMER)
    r.setName(data.name)
    r.setAge(data.age)
    r.setAddress(data.address)
    r.setSalary(data.salary.bigDecimal)
    r.store()
    new Customer(r)
  }

  def getCustomerById(id: Int): Customer = {
    val c = dbContext.fetchOne(CUSTOMER, CUSTOMER.ID.eq(id))
    new Customer(c)
  }
}
