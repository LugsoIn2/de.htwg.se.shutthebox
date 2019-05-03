package de.htwg.se.shutthebox.model

import scala.io.StdIn.readLine

class Player() {
  var plrName = ""
  var score = 0

  def inputName(index:Integer) : String = {
    printf("Player %d: ",index)
    plrName = readLine()
    plrName
  }

  def updateScore(n:Integer) : Integer = {
    score = n
    n
  }
}
