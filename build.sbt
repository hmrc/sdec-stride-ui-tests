ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision

lazy val root = (project in file("."))
  .settings(
    name := "sdec-stride-ui-tests",
    version := "0.1.0",
    scalaVersion := "3.3.8",
    libraryDependencies ++= Dependencies.test,
    Compile / scalafmtOnCompile := true,
    Test / scalafmtOnCompile := true,
    (Compile / compile) := ((Compile / compile) dependsOn (
      Compile / scalafmtSbtCheck,
      Compile / scalafmtCheckAll
    )).value
  )

// @formatter:off
addCommandAlias("prePrChecks", "; scalafmtCheckAll; scalafmtSbtCheck; scalafixAll --check; --check")
addCommandAlias("lint", "; scalafmtAll; scalafmtSbt; scalafixAll")
addCommandAlias("prePush", "; reload; clean; compile; test; it/test; lint;")
// @formatter:on
