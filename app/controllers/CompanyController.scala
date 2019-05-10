package controllers

import javax.inject.Inject
import models.{Company, Customer}
import play.api.data._
import play.api.mvc._
import services.CompanyService

class CompanyController @Inject()(cc: MessagesControllerComponents, companyService: CompanyService)
	extends MessagesAbstractController(cc) {

	import CompanyForm._

	private var companies: Seq[Company] = Seq()

	// The URL to the widget.  You can call this directly from the template, but it
	// can be more convenient to leave the template completely stateless i.e. all
	// of the "WidgetController" references are inside the .scala file.
	private val postUrl = routes.CompanyController.createCompany()

	def listCompanies = Action { implicit request: MessagesRequest[AnyContent] =>
		companies = companyService.getAllCompanies()
		Ok(views.html.listCompanies(companies, form, postUrl))
	}

	// This will be the action that handles our form post
	def createCompany = Action { implicit request: MessagesRequest[AnyContent] =>
		val errorFunction = { formWithErrors: Form[Data] =>
			// This is the bad case, where the form had validation errors.
			// Let's show the user the form again, with the errors highlighted.
			// Note how we pass the form with errors to the template.
			BadRequest(views.html.listCompanies(companies, formWithErrors, postUrl))
		}

		val successFunction = { data: Data =>
			// This is the good case, where the form was successfully parsed as a Data object.
			companyService.storeCompany(data)
			Redirect(routes.CompanyController.listCompanies()).flashing("info" -> "Company added!")
		}

		val formValidationResult = form.bindFromRequest
		formValidationResult.fold(errorFunction, successFunction)
	}
}
