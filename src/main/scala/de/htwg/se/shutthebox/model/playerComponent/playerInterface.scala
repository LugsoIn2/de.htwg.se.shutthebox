package de.htwg.se.shutthebox.model.playerComponent

trait playerInterface {
  var plrName:String
  var score:Integer

  def setName(index:Integer):String
  def updateScore(n:Integer):Integer
}
