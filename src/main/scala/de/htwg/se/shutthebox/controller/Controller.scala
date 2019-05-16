package de.htwg.se.shutthebox.controller

import de.htwg.se.shutthebox.aview.TUI
import de.htwg.se.shutthebox.model._
import de.htwg.se.shutthebox.util.Observable
import de.htwg.se.shutthebox.controller.GameState._


class Controller(var matchfield: Field, var dice: Array[Die]) extends Observable {
  var players = Array(new Player, new Player)
  var currentPlayer = players(0)
  //var gameState = GameState.MENU
  var gameState : GameState = MENU
  var validNumber = Array(0,0)


  def startGame(): Unit = {
    createField()
    var players = createPlayers()
    //print(printStartGame())
    getPlayers()(0).setName(1)   // problems with code coverage
    getPlayers()(1).setName(2)   // NullPointerException or infinite loop for input
    setCurrentPlayer()
    //gameState = GameState.INGAME
    gameState=INGAME
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
    } else {
      index = 1
    }
    currentPlayer = players(index)
    notifyObservers
  }


  def doShut(i:Int) : Unit = {
    if (gameState == ROLLDICE | gameState == SHUT) {
      if (validNumber(0) == i | validNumber(1) == i) {
        shut(i)
      } else {
        print("Dieser Shut ist nicht erlaubt")
      }
    } else {
      print("Bitte erst Würfeln (shut nicht erlaubt)")
    }
  }

  def shut(i : Int) : Unit = {
    matchfield.shut(i, matchfield)
    //gameState = GameState.INGAME
    //gameState=INGAME
    gameState=SHUT
    notifyObservers

  }


  def rollDice() : Unit = {
    if (gameState == INGAME | gameState == SHUT){
      dice(0).roll
      Thread.sleep(40)
      dice(1).roll
      getValidShuts()
      //gameState = GameState.ROLLDICE
      gameState=ROLLDICE
      notifyObservers
    } else {
      print("Würfeln nicht erlaubt")
    }
  }

  def getValidShuts() : Unit = {
    //Aufruf der regeln methode.
    //Soll in validNumber dann die erlaubten Zahlen schreiben
    // 1, 3
    validNumber(0) = 1
    validNumber(1) = 3
  }


  def printOutput() : String = {
    gameState match {
      case GameState.MENU => ""
      case GameState.ROLLDICE => rollToString
      case GameState.SHUT => fieldToString
      case GameState.INGAME => fieldToString


    }
  }


  def fieldToString : String = matchfield.toString

  def rollToString : String =  {
    dice(0).toString + dice(1).toString
  }

}
