name := "akka-persistance-example"

version := "1.0"

scalaVersion := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.4.2"
  val testDependencies = Seq(
    "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
    "org.specs2" % "specs2-core_2.11" % "3.7" % "test",
    "org.mockito" % "mockito-all" % "1.9.5" % "test"
  )
  val prodDependencies = Seq(
    "org.slf4j" % "slf4j-simple" % "1.7.12",
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-persistence" % akkaV,
    "com.typesafe" % "config" % "1.3.0"
  )
  prodDependencies ++ testDependencies
}

mainClass in (Compile,run)  := Some("Boot")