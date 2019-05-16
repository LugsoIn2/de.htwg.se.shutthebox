package de.htwg.se.shutthebox.controller

import de.htwg.se.shutthebox.aview.TUI
import de.htwg.se.shutthebox.model._
import de.htwg.se.shutthebox.util.Observable
import de.htwg.se.shutthebox.controller.GameState._
import de.htwg.se.shutthebox.controller.ShutState._


class Controller(var matchfield: Field, var dice: Array[Die]) extends Observable {
  var players = Array(new Player, new Player)
  var currentPlayer = players(0)
  //var gameState = GameState.MENU
  var gameState : GameState = MENU
  var shutState : ShutState = SHUTSTATE0

  var validNumber = Array(0,0)
  var validSum = 0
  var validDiff = 0
  var validProd = 0
  var validDiv = 0


  def startGame(): Unit = {
    createField()
    var players = createPlayers()
    //print(printStartGame())
    getPlayers()(0).setName(1)   // problems with code coverage
    getPlayers()(1).setName(2)   // NullPointerException or infinite loop for input
    setCurrentPlayer()
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

      if((validNumber(0) == i | validNumber(1) == i) & shutState == SHUTSTATE0) {
        shut(i)
        shutState = SHUTSTATE5
      }else if((validNumber(0) == i | validNumber(1) == i) & shutState == SHUTSTATE5) {
        shut(i)
        shutState = SHUTSTATE5
      }else if(validSum == i & shutState == SHUTSTATE0) {
        shut(i)
        shutState = SHUTSTATE1
      } else if(validDiff == i & shutState == SHUTSTATE0) {
        shut(i)
        shutState = SHUTSTATE2
      }else if(validProd == i & shutState == SHUTSTATE0) {
        shut(i)
        shutState = SHUTSTATE3
      } else if(validDiv == i & shutState == SHUTSTATE0) {
        shut(i)
        shutState = SHUTSTATE4
      } else {
        print("Dieser Shut ist nicht erlaubt")
      }
    } else {
      print("Bitte erst Würfeln (shut nicht erlaubt)")
    }
  }

  /*def doShut(i:Int) : Unit = {
    if (gameState == ROLLDICE | gameState == SHUT) {
      if (validNumber(0) == i | validNumber(1) == i | validSum == i | validDiff == i | validProd == i | validDiv == i) {
        shut(i)
      } else {
        print("Dieser Shut ist nicht erlaubt")
      }
    } else {
      print("Bitte erst Würfeln (shut nicht erlaubt)")
    }
  }*/

  def shut(i : Int) : Unit = {
    matchfield.shut(i, matchfield)
    gameState=SHUT
    notifyObservers

  }
/*
  def shut(i : Int, i2 : Int) : Unit = {
    matchfield.shut(i, matchfield)
    if (i2 != 0) {
      matchfield.shut(i2, matchfield)
    }
    //gameState = GameState.INGAME
    //gameState=INGAME
    gameState=SHUT
    notifyObservers

  }*/


  def getValidShuts() : Unit = {
    //Aufruf der regeln methode.
    //Soll in validNumber dann die erlaubten Zahlen schreiben
    // 1, 3
    validNumber(0) = 3
    validNumber(1) = 1
    validSum = 4
    validProd = 3
    validDiff = 2
    validDiv = 3
  }


  def rollDice() : Unit = {
    if (gameState == INGAME | gameState == SHUT){
      dice(0).roll
      Thread.sleep(38)
      dice(1).roll
      getValidShuts()
      gameState=ROLLDICE
      shutState=SHUTSTATE0
      notifyObservers
    } else {
      print("Würfeln nicht erlaubt")
    }
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
