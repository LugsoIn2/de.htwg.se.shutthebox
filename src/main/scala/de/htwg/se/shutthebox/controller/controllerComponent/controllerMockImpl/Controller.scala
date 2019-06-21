package de.htwg.se.shutthebox.controller.controllerComponent.controllerMockImpl

import de.htwg.se.shutthebox.controller.controllerComponent.ControllerInterface
import de.htwg.se.shutthebox.model.fieldComponent.fieldInterface
import de.htwg.se.shutthebox.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.shutthebox.model.playerComponent.playerImpl.Player

class Controller extends ControllerInterface {

  var field = new Field
  var players:Array[Player] = Array.ofDim[Player](2)

  override def startGame(t: Integer, ai: Boolean): Unit = {}

  override def createField(t: Integer): Unit = {}

  override def getField: fieldInterface = field

  override def createDice(): Unit = {}

  override def createPlayers(ai: Boolean): Unit = {}

  override def getPlayers: Array[Player] = players

  override def getCurrentPlayer: Player = players(0)

  override def setCurrentPlayer(): Unit = {}

  override def getScore: Int = 1

  override def resetMatchfield(): Unit = {}

  override def cmdShut(value: Int): Unit = {}

  override def cmdUnShut(): Unit = {}

  override def cmdRedoShut(): Unit = {}

  override def redoShut(): Unit = {}

  override def undoShut(): Unit = {}

  override def doShut(i: Int): String = ""

  override def shut(i: Int): Unit = {}

  override def calcValidShuts(): Unit = {}

  override def calcSum: Integer = 1

  override def calcDiff: Integer = 1

  override def calcProd: Integer = 1

  override def calcDiv: Integer = 1

  override def rollDice: String = ""

  override def printOutput: String = ""

  override def fieldToString: String = ""

  override def rollToString: String = ""
}
