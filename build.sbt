name := """play-jooq"""
organization := "io.sqooba"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"
val jooqVersion = "3.11.11"

libraryDependencies ++= Seq(
  guice,
  "org.jooq"          %   "jooq"                  % jooqVersion,
  "org.jooq"          %   "jooq-meta"             % jooqVersion,
  "org.jooq"          %   "jooq-codegen"          % jooqVersion,
  "org.jooq"          %%  "jooq-scala"            % jooqVersion,
  "org.postgresql"    %   "postgresql"            % "42.2.5",
  "io.sqooba"         %%  "sq-conf"               % "0.5.1",
  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.2" % Test
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "io.sqooba.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "io.sqooba.binders._"

enablePlugins(PackPlugin)