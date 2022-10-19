import sbt.Keys.{libraryDependencies, scalacOptions}

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.11.8"

lazy val root = (project in file("."))
  .settings(
    name := "motionBlur",
    scalacOptions ++= Seq("-deprecation", "-feature"),
    libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "2.2.4" % "test",
      "junit" % "junit" % "4.10" % "test")
  )

