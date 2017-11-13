import com.typesafe.config.ConfigFactory


lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    inThisBuild(Seq(
      organization := "$organization$",
      scalaVersion := "2.12.4"
    )),
    name := "$name$",
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      jdbc,
      cache,
      ws,
      "com.typesafe.slick" %% "slick" % "3.2.1",
      "com.typesafe.play" %% "play-slick" % "3.0.2",
      "com.h2database" % "h2" % "1.4.196",
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
    ),
    routesGenerator := InjectedRoutesGenerator,
    Conf.dbConf := {
      val cfg = ConfigFactory.parseFile((resourceDirectory in Compile).value / "application.conf")
      val prefix = "slick.dbs.default"
      (cfg.getString(s"$prefix.db.url"), cfg.getString(s"$prefix.user"), cfg.getString(s"$prefix.password"))
    }
  )
