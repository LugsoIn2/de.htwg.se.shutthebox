package de.htwg.se.shutthebox.controller

import de.htwg.se.shutthebox.aview.TUI
import de.htwg.se.shutthebox.model._
import de.htwg.se.shutthebox.util.Observable

class Controller(var matchfield: Field, var dice: Array[Die], var players: Array[Player]) extends Observable {
  def createField() : Field = {
    matchfield = new Field()
    notifyObservers
    matchfield
  }

  def createDice(): Array[Die] = {
    dice = Array(new Die, new Die)
    notifyObservers
    dice
  }

  def createPlayers(): Array[Player] = {
    players = Array(new Player, new Player)
    notifyObservers
    players
  }

  def setCurrentPlayer(index:Integer): Player = {
    var currentPlayer = players(index)
    currentPlayer
  }




  def gridToString: String = matchfield.toString


}
