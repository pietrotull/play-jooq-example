package controllers

import javax.inject.Inject
import models.Company
import play.api.data._
import play.api.mvc._
import services.CompanyService

class CompanyController @Inject()(cc: MessagesControllerComponents, companyService: CompanyService)
	extends MessagesAbstractController(cc) {

	import CompanyForm._

	private val widgets = scala.collection.mutable.ArrayBuffer(
		Company("Widget 1", "Add 1"),
		Company("Widget 2", "add 2"),
		Company("Widget 3", "add 3")
	)

	// The URL to the widget.  You can call this directly from the template, but it
	// can be more convenient to leave the template completely stateless i.e. all
	// of the "WidgetController" references are inside the .scala file.
	private val postUrl = routes.CompanyController.createCompany()

	def index = Action {
		val comps = companyService.getAllCompanies()
		comps.foreach(c => {
			c.getId
		})
		Ok(views.html.index(comps))
	}

	def listCompanies = Action { implicit request: MessagesRequest[AnyContent] =>
		// Pass an unpopulated form to the template
		Ok(views.html.listCompanies(widgets, form, postUrl))
	}

	// This will be the action that handles our form post
	def createCompany = Action { implicit request: MessagesRequest[AnyContent] =>
		val errorFunction = { formWithErrors: Form[Data] =>
			// This is the bad case, where the form had validation errors.
			// Let's show the user the form again, with the errors highlighted.
			// Note how we pass the form with errors to the template.
			BadRequest(views.html.listCompanies(widgets, formWithErrors, postUrl))
		}

		val successFunction = { data: Data =>
			// This is the good case, where the form was successfully parsed as a Data object.
			val widget = Company(name = data.name, address = data.address)
			widgets.append(widget)
			Redirect(routes.CompanyController.listCompanies()).flashing("info" -> "Widget added!")
		}

		val formValidationResult = form.bindFromRequest
		formValidationResult.fold(errorFunction, successFunction)
	}
}
