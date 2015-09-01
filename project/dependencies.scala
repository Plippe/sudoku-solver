package com.github.plippe.steam.build

import sbt._
import sbt.Keys._

object Dependencies {
  lazy val common = Seq(scalaTest)

  val scalaTest = "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
}
