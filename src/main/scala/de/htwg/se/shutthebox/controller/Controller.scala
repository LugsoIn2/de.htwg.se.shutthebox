package de.htwg.se.shutthebox.controller

import java.util
import scala.collection.mutable.Stack

import de.htwg.se.shutthebox.aview.TUI
import de.htwg.se.shutthebox.model._
import de.htwg.se.shutthebox.util.{Observable, UndoManager}
import de.htwg.se.shutthebox.controller.GameState._
import de.htwg.se.shutthebox.controller.ShutState._
import scala.swing.Publisher


class Controller() extends Publisher {
  //var players = Array(new Player, new Player)
  var players = Array.ofDim[Player](2)
  var currentPlayer = players(0)
  var currentPlayerIndex = 0 // to determine, when to show scoreboard
  var matchfield : AbstractField = _
  var dice = Array(new Die, new Die)
  var gameState : GameState = MENU
  var shutState : ShutState = SHUTSTATE0

  var validNumber = Array(0,0)
  var validSum = 0
  var validDiff = 0
  var validProd = 0
  var validDiv = 0

  private val undoManager = new UndoManager
  private var lastShut = Stack[Int]()
  private var tmpLastShut = Stack[Int]()


  def startGame(t:Integer, ai:Boolean): Unit = {
    //t 0 = SmallField, t 1 = BigField
    //ai 0 = no AI, ai 1 = AI
    createField(t)
    createPlayers(ai)
    //print(printStartGame())
    getPlayers()(0).setName(1)   // problems with code coverage
    getPlayers()(1).setName(2)   // NullPointerException or infinite loop for input
    //setCurrentPlayer()
    gameState=INGAME
  }


  def createField(t:Integer) : Unit = {
    if (t == 0)
      matchfield = new Field()
    else
      matchfield = new BigField()
    publish(new FieldCreated)
  }

  def getField() : AbstractField = {
    matchfield
  }

  def createDice(): Unit = {
    dice = Array(new Die, new Die)
    publish(new DiceCreated)
  }


  def createPlayers(ai:Boolean): Unit = {
    players(0) = new Player()
    if (ai) {
      players(1) = new AI(this)
    }
    else {
      players(1) = new Player()
    }
    currentPlayer = players(0)
    currentPlayerIndex = 0
    publish(new PlayersCreated)
  }

  def getPlayers(): Array[Player] = {
    players
  }

  def getCurrentPlayer() : Player = {
    currentPlayer
  }

  def setCurrentPlayer(): Unit = {
    currentPlayerIndex += 1

    if (currentPlayerIndex < 2) {
    currentPlayer.updateScore(getScore())
    resetMatchfield()

    if (currentPlayer == players(0)) {
      currentPlayer = players(1)
      if (currentPlayer.isInstanceOf[AI]) {
        players(1).asInstanceOf[AI].think()
      }
    }
      publish(new CurrentPlayerSet)
  } else {
      currentPlayer.updateScore(getScore())
      publish(new ShowScoreBoard)
    }

  }

  def getScore() : Int = {
    var score = 0
    for (i <- 1 to matchfield.field.size) {
      score += i
      if (matchfield.field(i-1).isShut == true) {
        score -= i
      }
    }
    publish(new ScoreUpdated)
    score
  }

  def resetMatchfield() : Unit = {
    for (i <- 1 to matchfield.field.size) {
      matchfield.field(i-1).isShut = false;
    }
  }


  def cmdShut(value:Int) = {
    undoManager.doStep(new SetCommand(value, this))
  }

  def cmdUnShut() = {
    undoManager.undoStep
  }

  def cmdRedoShut() = {
    undoManager.redoStep
  }

  def redoShut(): Unit = {
    if (!tmpLastShut.isEmpty) {
    doShut(tmpLastShut.top)
    tmpLastShut.pop()
  }
    publish(new Redone)
  }

  def undoShut(): Unit = {
    if (!lastShut.isEmpty) {
      shutState = SHUTSTATE0
      gameState = UNDOSTATE
      matchfield.field(lastShut.top - 1).isShut = false;
      tmpLastShut.push(lastShut.top)
      lastShut.pop()
    }
    publish(new Undone)
  }
  def doShut(i:Int) : String = {
    var message = " "
    if (gameState == ROLLDICE | gameState == SHUT | gameState == UNDOSTATE) {

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
        message = "This shut is not allowed"
        println(message)
      }
    } else {
      message = "Please roll the dice first!"
      println(message)
    }
    message
  }


  def shut(i : Int) : Unit = {
    matchfield.shut(i, matchfield)
    lastShut.push(i)
    //tmpLastShut.push(i)
    gameState=SHUT
    publish(new CellShut)

  }



  def getValidShuts() : Unit = {
    //Aufruf der regeln methode.
    //Soll in validNumber dann die erlaubten Zahlen schreiben
    // 1, 3
    validNumber(0) = dice(0).value
    validNumber(1) = dice(1).value
    validSum = calcSum
    validProd = calcProd
    validDiff = calcDiff
    validDiv = calcDiv
  }

  def calcSum() : Integer = {
    var i = dice(0).value
    var j = dice(1).value

    i + j
  }

  def calcDiff() : Integer = {
    var res = 0
    var i = dice(0).value
    var j = dice(1).value

    if (i > j) {
      res = i - j
    } else if (i < j) {
      res = j - i
    }
    res
  }

  def calcProd() : Integer = {
    var i = dice(0).value
    var j = dice(1).value

    i * j
  }

  def calcDiv() : Integer = {
    var res = 0
    var i = dice(0).value
    var j = dice(1).value

    if (i > j) {
      res = i / j
    } else if (i < j) {
      res = j / i
    } else if (i == j) {
      res = 1
    }
    res.toInt
  }

  def rollDice() : String = {
    var message = " "
    lastShut.clear()
    tmpLastShut.clear()
    if (gameState == INGAME | gameState == SHUT){
      dice(0).roll
      Thread.sleep(100)
      dice(1).roll
      getValidShuts()
      gameState=ROLLDICE
      shutState=SHUTSTATE0
      publish(new DiceRolled)
    } else {
      message = "Dice roll not allowed!"
      println(message)
    }
    message
  }

  def printOutput() : String = {
    gameState match {
      case GameState.MENU => ""
      case GameState.ROLLDICE => rollToString
      case GameState.SHUT => fieldToString
      case GameState.INGAME => fieldToString
      case GameState.UNDOSTATE => fieldToString
    }
  }

  def fieldToString : String = matchfield.toString

  def rollToString : String =  {
    dice(0).toString + dice(1).toString
  }

}
