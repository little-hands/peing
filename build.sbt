name := "peing"

version := "0.1"

scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  "com.lihaoyi" %% "requests" % "0.5.1",
  "com.lihaoyi" %% "ujson" % "1.0.0",

  "com.typesafe.play" %% "play-json" % "2.8.0",

  "org.scalatest" %% "scalatest" % "3.1.0" % "test"
)
