package controllers

import javax.inject.Inject
import models.Customer
import play.api.data._
import play.api.mvc._
import services.CustomerService

/*
import javax.inject.Inject
import models.Company
import play.api.data._
import play.api.mvc._
import services.CompanyService
 */

class CustomerController @Inject()(cc: MessagesControllerComponents, customerService: CustomerService)
  extends MessagesAbstractController(cc) {

  import CustomerForm._

  private val widgets = scala.collection.mutable.ArrayBuffer(
    Customer(1, "Dang McNugget", 20, "Address 10", 100),
    Customer(2, "Cusy Customer", 25, "Address 12", 100),
  )

  // The URL to the widget.  You can call this directly from the template, but it
  // can be more convenient to leave the template completely stateless i.e. all
  // of the "WidgetController" references are inside the .scala file.
  private val postUrl = routes.CustomerController.createCustomer()

  def index = Action {
    val comps = customerService.getAllCustomers()
    Ok(views.html.index(comps))
  }

  def listCustomers = Action { implicit request: MessagesRequest[AnyContent] =>
    // Pass an unpopulated form to the template
    Ok(views.html.listCustomers(widgets, form, postUrl))
  }

  // This will be the action that handles our form post
  def createCustomer = Action { implicit request: MessagesRequest[AnyContent] =>
    val errorFunction = { formWithErrors: Form[Data] =>
      // This is the bad case, where the form had validation errors.
      // Let's show the user the form again, with the errors highlighted.
      // Note how we pass the form with errors to the template.
      BadRequest(views.html.listCustomers(widgets, formWithErrors, postUrl))
    }

    val successFunction = { data: Data =>
      // This is the good case, where the form was successfully parsed as a Data object.
      val widget = Customer(id = data.id, name = data.name, age = data.age, address = data.address, salary = data.salary)
      widgets.append(widget)
      Redirect(routes.CustomerController.listCustomers()).flashing("info" -> "Customer added!")
    }

    val formValidationResult = form.bindFromRequest
    formValidationResult.fold(errorFunction, successFunction)
  }
}
