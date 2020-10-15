package de.htwg.se.shutthebox.model.playerComponent.playerImpl

import de.htwg.se.shutthebox.model.playerComponent.playerInterface

class Player() extends playerInterface {
  var plrName = ""
  var score = 0

  def setName(index:Integer) : String = {
    plrName = "Player " + index
    plrName
  }

  def updateScore(n:Integer) : Integer = {
    score = n
    score
  }
}
