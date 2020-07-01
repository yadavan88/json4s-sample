name := "json4s-sample"

version := "0.1"

scalaVersion := "2.12.11"

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-jackson" % "3.6.9",
  "org.scalatest" %% "scalatest" % "3.2.0" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.0" % Test
)