package controllers

import play.api.mvc._
import slick.basic.DatabaseConfig
import slick.jdbc.H2Profile


class MainController(components: ControllerComponents, dbConfig: DatabaseConfig[H2Profile])
  extends AbstractController(components) {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}
