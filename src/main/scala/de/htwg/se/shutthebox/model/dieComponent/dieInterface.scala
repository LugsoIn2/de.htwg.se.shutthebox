package de.htwg.se.shutthebox.model.dieComponent

trait dieInterface {
  val start:Int
  val end:Int
  var value:Int

  def roll:Integer
}
