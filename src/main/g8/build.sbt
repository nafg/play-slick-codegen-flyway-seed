import com.typesafe.config.ConfigFactory

ThisBuild / organization := "$organization$"
ThisBuild / scalaVersion := "2.12.6"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "$name$",
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      jdbc,
      ws,
      "com.typesafe.slick" %% "slick" % "3.2.3",
      "com.typesafe.play" %% "play-slick" % "3.0.3",
      "com.h2database" % "h2" % "1.4.197",
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
    ),
    Conf.dbConf := {
      val cfg = ConfigFactory.parseFile((resourceDirectory in Compile).value / "application.conf")
      val prefix = "slick.dbs.default"
      (cfg.getString(s"\$prefix.db.url"), cfg.getString(s"\$prefix.user"), cfg.getString(s"\$prefix.password"))
    }
  )
