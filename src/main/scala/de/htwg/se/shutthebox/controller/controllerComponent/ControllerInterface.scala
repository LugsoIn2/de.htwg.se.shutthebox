package de.htwg.se.shutthebox.controller.controllerComponent

import de.htwg.se.shutthebox.controller.controllerComponent.GameState.{GameState, MENU}
import de.htwg.se.shutthebox.controller.controllerComponent.ShutState.{SHUTSTATE0, ShutState}
import de.htwg.se.shutthebox.model.fieldComponent.fieldBaseImpl.Die
import de.htwg.se.shutthebox.model.fieldComponent.fieldInterface
import de.htwg.se.shutthebox.model.playerComponent.playerImpl.Player
import de.htwg.se.shutthebox.model.playerComponent.playerInterface
import de.htwg.se.shutthebox.util.UndoManager

import scala.collection.mutable
import scala.swing.Publisher

trait ControllerInterface extends Publisher {

  var players:Array[playerInterface]
  var currentPlayer:playerInterface
  var currentPlayerIndex : Int // to determine, when to show scoreboard
  var matchfield : fieldInterface
  var dice : Array[Die]
 /* var gameState : GameState
  var shutState : ShutState

  var validNumber : Array[Int]
  var validSum : Int
  var validDiff : Int
  var validProd : Int
  var validDiv : Int

  private val undoManager = new UndoManager
  private var lastShut = mutable.Stack[Int]()
  private var tmpLastShut = mutable.Stack[Int]()*/

  def startGame(t:Integer, ai:Boolean): Unit
  def createField(t:Integer):Unit
  def getField:fieldInterface
  def createDice():Unit
  def createPlayers(ai:Boolean): Unit
  def getPlayers: Array[playerInterface]
  def getCurrentPlayer : playerInterface
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
  def load: Unit
  def save:Unit
  def update:Unit
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
class AIThink extends Event
class GameLoaded extends Event
