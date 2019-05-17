package de.htwg.se.shutthebox.model

import scala.io.StdIn.readLine

class Player() {
  var plrName = ""
  var score = 0

  def setName(index:Integer) : String = {
    printf("Player %d: ",index)
    plrName = "Player"//readLine()
    plrName
  }

  def updateScore(n:Integer) : Integer = {
    score = n
    score
  }

}
