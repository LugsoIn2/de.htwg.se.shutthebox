package de.htwg.se.shutthebox.controller.controllerComponent

import de.htwg.se.shutthebox.model.fieldComponent.fieldInterface
import de.htwg.se.shutthebox.model.playerComponent.playerImpl.Player

import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  def startGame(t:Integer, ai:Boolean): Unit
  def createField(t:Integer):Unit
  def getField:fieldInterface
  def createDice():Unit
  def createPlayers(ai:Boolean): Unit
  def getPlayers: Array[Player]
  def getCurrentPlayer : Player
  def setCurrentPlayer() : Unit
  def getScore : Int
  def resetMatchfield() : Unit
  def cmdShut(value:Int):Unit
  def cmdUnShut() : Unit
  def cmdRedoShut() : Unit
  def redoShut() : Unit
  def undoShut() : Unit
  def doShut(i:Int) : String
  def shut(i:Int) : Unit
  def calcValidShuts() : Unit
  def calcSum : Integer
  def calcDiff : Integer
  def calcProd : Integer
  def calcDiv : Integer
  def rollDice : String
  def printOutput : String
  def fieldToString : String
  def rollToString : String
}

import scala.swing.event.Event

class DiceRolled extends Event
class FieldCreated extends Event
class DiceCreated extends Event
class PlayersCreated extends Event
class CurrentPlayerSet extends Event
class Redone extends Event
class Undone extends Event
class CellShut extends Event
class AllCellsShut extends Event
class ScoreUpdated extends Event
class ShowScoreBoard extends Event
