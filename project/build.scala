package com.github.plippe.steam.build

import sbt._
import sbt.Keys._

import com.typesafe.sbt.SbtScalariform._

object SteamBuild extends Build {
  val commonSettings: Seq[Setting[_]] = {
    Seq (
      version      := "1.0-SNAPSHOT",
      scalaVersion := "2.11.7"
    ) ++ scalariformSettings
  }

  def newProject(id: String, settings: Seq[Setting[_]]): Project =
    Project(id, file(id)).settings(commonSettings ++ settings: _*)
}
