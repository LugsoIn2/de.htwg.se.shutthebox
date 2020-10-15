package de.htwg.se.shutthebox.model.playerComponent

import scala.concurrent.Future
import scala.swing.Frame

trait aiInterface {
  var validShuts:Array[Int]
  var singleShuts:Array[Int]
  var allowFuture:Boolean

  def randomTimeMillis(min:Int, max:Int):Int
  def calcValidShuts():Unit
  def giveUp():Unit
  def analyze():Future[Unit]
  def think():Unit
}
