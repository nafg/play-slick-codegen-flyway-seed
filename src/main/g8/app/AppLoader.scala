import play.api._
import play.api.db.slick.{DbName, SlickComponents}
import slick.jdbc.H2Profile

import router.Routes


class AppLoader extends ApplicationLoader {
  def load(context: ApplicationLoader.Context) =
    new MyComponents(context).application

}

class MyComponents(context: ApplicationLoader.Context)
  extends BuiltInComponentsFromContext(context)
    with play.filters.HttpFiltersComponents
    with _root_.controllers.AssetsComponents
    with SlickComponents {


  lazy val router = new Routes(
    httpErrorHandler,
    new _root_.controllers.MainController(controllerComponents, slickApi.dbConfig[H2Profile](DbName("default"))),
    assets
  )
}
