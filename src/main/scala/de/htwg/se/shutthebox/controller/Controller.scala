package de.htwg.se.shutthebox.controller

import de.htwg.se.shutthebox.aview.TUI
import de.htwg.se.shutthebox.model._
import de.htwg.se.shutthebox.util.Observable
import de.htwg.se.shutthebox.controller.GameState._


class Controller(var matchfield: Field, var dice: Array[Die]) extends Observable {
  var players = Array(new Player, new Player)
  var currentPlayer = players(0)
  var gameState = GameState.MENU


  def startGame(): Unit = {
    createField()
    var players = createPlayers()
    //print(printStartGame())
    getPlayers()(0).setName(1)   // problems with code coverage
    getPlayers()(1).setName(2)   // NullPointerException or infinite loop for input
    setCurrentPlayer()
    gameState = GameState.INGAME
  }


  def createField() : Unit = {
    matchfield = new Field()
    notifyObservers
  }

  def getField() : Field = {
    matchfield
  }

  def createDice(): Unit = {
    dice = Array(new Die, new Die)
    notifyObservers
  }


  def createPlayers(): Unit = {
    currentPlayer = players(0)
    notifyObservers
  }

  def getPlayers(): Array[Player] = {
    players
  }

  def getCurrentPlayer() : Player = {
    notifyObservers
    currentPlayer
  }

  def setCurrentPlayer(): Unit = {
    var index = 0
    if (currentPlayer == players(0)) {
      index = 0
    }
    else {
      index = 1
    }
    currentPlayer = players(index)
    notifyObservers
  }

  def shut(i : Int) : Unit = {
    matchfield.shut(i, matchfield)
    notifyObservers
    gameState = GameState.INGAME

  }

  def rollDice() : Unit = {
    dice(0).roll
    Thread.sleep(20)
    dice(1).roll
    notifyObservers
    gameState = GameState.SHUT


  }
  def getDieValue(index : Integer) : Integer = {
    rollDice()
    dice(index).value
  }

  def printOutput() : String = {
    gameState match {
      case GameState.MENU => ""
      case GameState.ROLL => rollToString
      case GameState.SHUT => fieldToString
      case GameState.INGAME => ""


    }
  }

  def matchfieldToString : String = matchfield.toString


  def fieldToString : String = matchfield.toString
  def rollToString : String =  {
    dice(0).toString + dice(1).toString
  }

}
