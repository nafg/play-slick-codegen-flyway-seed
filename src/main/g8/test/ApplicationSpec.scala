import play.api.test.Helpers._
import play.api.test._

import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.play._


/**
  * Runs an integration test with an application
  */
class ApplicationSpec extends PlaySpec
  with BaseOneAppPerTest
  with MyApplicationFactory
  with ScalaFutures {

  "Routes" should {
    "send 404 on a bad request" in {
      route(app, FakeRequest(GET, "/boum")).map(status) mustBe Some(NOT_FOUND)
    }
  }

  "MainController" should {
    "render the index page" in {
      val home = route(app, FakeRequest(GET, "/")).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Your new application is ready.")
    }
  }
}
