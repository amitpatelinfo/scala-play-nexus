name := """scala-play-nexus"""

version := "2.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


publishTo <<= version { (v: String) =>
  if (v.trim.endsWith("SNAPSHOT"))
    Some("Test snapshots" at "http://localhost:5000/nexus/content/repositories/snapshots")
  else
    Some("Test releases" at "http://localhost:5000/nexus/content/repositories/releases")
}

credentials += Credentials(
    "Sonatype Nexus Repository Manager", "localhost", "admin", "admin123"
  )

publishMavenStyle := true

aetherSettings
