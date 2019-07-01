package controllers

import org.mockito.Mockito._
import org.scalatest.BeforeAndAfter
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import services.{CompanyService, CustomerService}

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting with MockitoSugar with BeforeAndAfter {

  val comMock: CompanyService = mock[CompanyService]
  val cusMock: CustomerService = mock[CustomerService]

  val controller = new HomeController(stubControllerComponents(), comMock, cusMock)

  after {
    reset(comMock, cusMock)
  }

  "HomeController GET" should {

    "render the index page from a new instance of controller" in {
      when(comMock.getAllCompanies).thenReturn(Seq())
      when(cusMock.getAllCustomers).thenReturn(Seq())

      val controller = new HomeController(stubControllerComponents(), comMock, cusMock)
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play-Jooq")
    }

    "render the index page from the application" in {
      val controller = inject[HomeController]
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play-Jooq")
    }

    "render the index page from the router" in {
      val request = FakeRequest(GET, "/")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play-Jooq")
    }
  }
}
