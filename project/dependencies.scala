package com.github.plippe.steam.build

import sbt._
import sbt.Keys._

object Dependencies {
  lazy val common = Seq(scalaTest, typeSafeLogging, slf4j)
  lazy val model = common ++ Seq()

  val scalaTest = "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

  // Logging
  val typeSafeLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
  val slf4j = "org.slf4j" % "slf4j-simple" % "1.7.12"
}
