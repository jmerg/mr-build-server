enablePlugins(JavaAppPackaging, AshScriptPlugin)
enablePlugins(DockerSpotifyClientPlugin)

lazy val commonSettings = Seq(
  version := "0.1",
  scalaVersion := "2.12.6",
  organization := "mr",
  name := "mr-build-server"
)

lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(
    commonSettings,
    Defaults.itSettings,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "it,test",
    libraryDependencies += "com.spotify" % "docker-client" % "8.11.2" % "it,test",
    libraryDependencies += "com.typesafe.akka" %% "akka-http"   % "10.1.1",
    libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.11"
  )

dockerBaseImage := "openjdk:8-jre-alpine"
dockerUpdateLatest := true
packageName in Docker := "mr-build-server"