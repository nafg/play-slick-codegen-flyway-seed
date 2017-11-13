import sbt._


object Conf {
  lazy val dbConf = settingKey[(String, String, String)]("Database url/user/password for slick-codegen and flyway")
}
