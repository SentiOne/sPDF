import SonatypeKeys._

releaseSettings

sonatypeSettings

name := "sPDF"

description := "Create PDFs using plain old HTML+CSS. Uses wkhtmltopdf on the back-end which renders HTML using Webkit."

homepage := Some(url("https://github.com/cloudify/sPDF"))

startYear := Some(2013)

licenses := Seq(
  ("MIT", url("http://opensource.org/licenses/MIT"))
)

organization := "io.github.cloudify"

/* scala versions and options */
scalaVersion := "2.10.6"

crossScalaVersions := Seq("2.9.3", "2.10.6", "2.11.7")

// release cross builds
ReleaseKeys.crossBuild := true

// These options will be used for *all* versions.
scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-encoding", "UTF-8"
  // "-Xcheckinit" // for debugging only, see https://github.com/paulp/scala-faq/wiki/Initialization-Order
  // "-optimise"   // this option will slow your build
)

scalacOptions ++= Seq(
  "-Yclosure-elim",
  "-Yinline"
)

// These language flags will be used only for 2.10.x.
// Uncomment those you need, or if you hate SIP-18, all of them.
scalacOptions <++= scalaVersion map { sv =>
  if (sv startsWith "2.10") List(
    "-Xverify",
    "-Ywarn-all",
    "-feature"
    // "-language:postfixOps",
    // "-language:reflectiveCalls",
    // "-language:implicitConversions"
    // "-language:higherKinds",
    // "-language:existentials",
    // "-language:experimental.macros",
    // "-language:experimental.dynamics"
  )
  else Nil
}

fork in Test := true

parallelExecution in Test := false

/* sbt behavior */
logLevel in compile := Level.Warn

traceLevel := 5

offline := false

scmInfo := Some(
  ScmInfo(
    url("https://github.com/cloudify/sPDF"),
    "scm:git:https://github.com/cloudify/sPDF.git",
    Some("scm:git:git@github.com:cloudify/sPDF.git")
  )
)

/* dependencies */
libraryDependencies ++= Seq (
  "org.mockito"     %  "mockito-all"    % "1.10.8" % "test"
)

def scalatestDependency(scalaVersion: String) = scalaVersion match {
  case v if v.startsWith("2.9") =>  "org.scalatest" %% "scalatest"  % "1.9.2" % "test"
  case _ =>                         "org.scalatest" %% "scalatest"  % "2.2.2" % "test"
}

// use different versions of scalatest for different versions of scala
libraryDependencies <+= scalaVersion(scalatestDependency(_))

// add scala-xml dependency when needed (for Scala 2.11 and newer) in a robust way
// this mechanism supports cross-version publishing
// taken from: http://github.com/scala/scala-module-dependency-sample
libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    // if scala 2.11+ is used, add dependency on scala-xml module
    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
      libraryDependencies.value ++ Seq(
        "org.scala-lang.modules" %% "scala-xml" % "1.0.1",
        "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.1"
      )
    case _ =>
      libraryDependencies.value
  }
}

/* publishing */
publishMavenStyle := true

externalResolvers := Seq(
  "Nexus Proxy Repository" at "http://nexus-office.sentione.com:8086/nexus/content/groups/public"
)

publishTo <<= version { (v: String) =>
  Some("releases" at "http://nexus-office.sentione.com:8086/nexus/content/repositories/chimeo_dep")
}

credentials += Credentials(Path.userHome / ".nexusCredentials")

publishArtifact in Test := false

// publishArtifact in (Compile, packageDoc) := false

// publishArtifact in (Compile, packageSrc) := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <developers>
    <developer>
      <id>cloudify</id>
      <name>Federico Feroldi</name>
      <email>pix@yahoo.it</email>
      <url>http://www.pixzone.com</url>
    </developer>
  </developers>
)

// Josh Suereth's step-by-step guide to publishing on sonatype
// http://www.scala-sbt.org/using_sonatype.html

site.settings

site.includeScaladoc()

ghpages.settings

git.remoteRepo := "https://github.com/SentiOne/sPDF.git"

seq(lsSettings:_*)

(LsKeys.tags in LsKeys.lsync) := Seq("pdf", "webkit")
