name := """playground-mongo"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "org.mongodb" %% "casbah" % "2.8.1",
  "com.github.tototoshi" %% "play-scalate" % "0.1.0-SNAPSHOT",
  "org.scalatra.scalate" %% "scalate-core" % "1.7.0",
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "org.mindrot" % "jbcrypt" % "0.3m"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
resolvers += "sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
resolvers += "jbcrypt repo" at "http://mvnrepository.com/"



// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

unmanagedResourceDirectories in Compile += baseDirectory.value / "app" / "views"
