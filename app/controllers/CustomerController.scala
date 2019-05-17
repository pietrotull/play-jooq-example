package controllers

import javax.inject.Inject
import models.Customer
import play.api.{Logger, Logging}
import play.api.data._
import play.api.mvc._
import services.CustomerService


class CustomerController @Inject()(cc: MessagesControllerComponents, customerService: CustomerService)
  extends MessagesAbstractController(cc) with Logging {

  import CustomerForm._

  private var customers: Seq[Customer] = Seq()

  // The URL to the widget.  You can call this directly from the template, but it
  // can be more convenient to leave the template completely stateless i.e. all
  // of the "WidgetController" references are inside the .scala file.
  private val postUrl = routes.CustomerController.createCustomer()


  def listCustomers = Action { implicit request: MessagesRequest[AnyContent] =>
    // Pass an unpopulated form to the template
    customers = customerService.getAllCustomers()
    Ok(views.html.listCustomers(customers, form, postUrl))
  }

  // This will be the action that handles our form post
  def createCustomer = Action { implicit request: MessagesRequest[AnyContent] =>
    val errorFunction = { formWithErrors: Form[Data] =>
      // This is the bad case, where the form had validation errors.
      // Let's show the user the form again, with the errors highlighted.
      // Note how we pass the form with errors to the template.
      BadRequest(views.html.listCustomers(customers, formWithErrors, postUrl))
    }

    val successFunction = { data: Data =>
      customerService.storeCustomer(data)
      // This is the good case, where the form was successfully parsed as a Data object.
      Redirect(routes.CustomerController.listCustomers()).flashing("info" -> "Customer added!")
    }

    val formValidationResult = form.bindFromRequest
    formValidationResult.fold(errorFunction, successFunction)
  }

  def editCustomer(id: Int) = Action { implicit request: MessagesRequest[AnyContent] => {}
    logger.info(s"edit customer: $id")
    val c = customerService.getCustomerById(id)
    val data = CustomerForm.Data(id = Some(c.id), name = c.name, age = c.age, address = c.address, salary = c.salary)
    Ok(views.html.listCustomers(customers, form.fill(data), postUrl))
  }

  def delCustomer(id: Int)  = Action { implicit request: MessagesRequest[AnyContent] => {}
    logger.info(s"delete customer: $id")
    customerService.delCustomer(id)
    Redirect(routes.CustomerController.listCustomers())
  }
}
