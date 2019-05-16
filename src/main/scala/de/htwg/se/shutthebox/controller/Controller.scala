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
    } else {
      index = 1
    }
    currentPlayer = players(index)
    notifyObservers
  }

  def shut(i : Int) : Unit = {
    if (gameState == GameState.ROLLDICE | gameState == GameState.INGAME) {
      if(getValidShuts() == true) {
        matchfield.shut(i, matchfield)
        gameState = GameState.INGAME
        notifyObservers
      } else {
        print("Bitte erst Würfeln (shut nicht erlaubt)")
      }
    } else {
      print("Bitte erst Würfeln (shut nicht erlaubt)")
    }

  }

  def getValidShuts() : Boolean = {
    //Aufruf der regeln methode. True wenn erlaubt, False wenn nicht
    //Nach jedem shut wird erneut gebueft, da manchmal 2 shuts manchmal nur einer erlaubt.
    // BSP: W1 = 2, W2 = 3 --> Erlaubt 2,3 (zwei Shuts) | 5 (ein shut) ......
    true
  }


  def rollDice() : Unit = {
    if (gameState == GameState.INGAME){
      dice(0).roll
      Thread.sleep(40)
      dice(1).roll
      gameState = GameState.ROLLDICE
      notifyObservers
    } else {
      print("Würfeln nicht erlaubt")
    }
  }


  def printOutput() : String = {
    gameState match {
      case GameState.MENU => ""
      case GameState.ROLLDICE => rollToString
      //case GameState.SHUT => fieldToString
      case GameState.INGAME => fieldToString


    }
  }

  def matchfieldToString : String = matchfield.toString


  def fieldToString : String = matchfield.toString
  def rollToString : String =  {
    dice(0).toString + dice(1).toString
  }

}
