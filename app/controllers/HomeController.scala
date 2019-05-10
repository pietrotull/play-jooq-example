package controllers

import generated.tables.records.CompanyRecord
import javax.inject._
import play.api._
import play.api.mvc._
import services.CompanyService

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, companyService: CompanyService) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    val comps = getCompanies()
    comps.foreach(c => {
      c.getId
    })
    Ok(views.html.index(comps))
  }

  def getCompanies(): List[CompanyRecord] = {
    companyService.getAllCompanies()
  }
}
