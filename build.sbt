ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.9"

lazy val root = (project in file("."))
  .settings(
    name := "Akka_cats"
  )

val akkaVersion = "2.6.14"
val akkaHttpVersion = "10.2.4"
val catsVersion = "2.8.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" % "akka-actor-typed_2.13" % akkaVersion,
  "com.typesafe.akka" % "akka-http_2.13" % akkaHttpVersion,
  "com.typesafe.akka" % "akka-http-spray-json_2.13" % akkaHttpVersion,
  "org.typelevel" %% "cats-core" % catsVersion
)