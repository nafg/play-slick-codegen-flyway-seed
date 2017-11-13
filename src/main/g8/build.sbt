import com.typesafe.config.ConfigFactory


lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    inThisBuild(Seq(
      organization := "$organization$",
      scalaVersion := "2.11.9"
    )),
    name := "$name$",
    version := "1.0-SNAPSHOT",
    resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
    libraryDependencies ++= Seq(
      jdbc,
      cache,
      ws,
      "com.typesafe.slick" %% "slick" % "3.0.2",
      "com.typesafe.play" %% "play-slick" % "1.0.1",
      "com.h2database" % "h2" % "1.4.188",
      specs2 % Test
    ),
    routesGenerator := InjectedRoutesGenerator,
    Conf.dbConf := {
      val cfg = ConfigFactory.parseFile((resourceDirectory in Compile).value / "application.conf")
      val prefix = "slick.dbs.default"
      (cfg.getString(s"\$prefix.url"), cfg.getString(s"\$prefix.user"), cfg.getString(s"\$prefix.password"))
    }
  )
