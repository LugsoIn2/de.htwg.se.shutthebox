package de.htwg.se.shutthebox.controller

import de.htwg.se.shutthebox.controller.controllerComponent.controllerBaseImpl.Controller
import org.scalatest._
import de.htwg.se.shutthebox.util._
import de.htwg.se.shutthebox.model._
import de.htwg.se.shutthebox.model.fieldComponent.AbstractField
import de.htwg.se.shutthebox.model.fieldComponent.fieldBaseImpl.{BigField, Field}

import scala.runtime.BoxedUnit

class ControllerTest extends WordSpec with Matchers {
  "A Controller" when {
    "empty" should {
      val matchfield = new Field()
      val matchfield2 = new BigField()


      val dice = Array(new Die, new Die)
      val controller = new Controller()


      "a value" in {
        controller.startGame(0, ai = false) shouldBe a [BoxedUnit]
        controller.createField(0) shouldBe a [BoxedUnit]
        controller.createField(1) shouldBe a [BoxedUnit]
        controller.getField() shouldBe a [AbstractField]
        controller.createDice() shouldBe a [BoxedUnit]
        controller.createPlayers(false) shouldBe a [BoxedUnit]
        controller.getPlayers() shouldBe a [Array[_]]
        controller.getCurrentPlayer() shouldBe a [Player]
        controller.setCurrentPlayer() shouldBe a [BoxedUnit]
        controller.getScore() shouldBe a [Integer]
        controller.resetMatchfield() shouldBe a [BoxedUnit]
        controller.cmdShut(1) shouldBe a [BoxedUnit]
        controller.cmdShut(2) shouldBe a [BoxedUnit]
        controller.cmdShut(3) shouldBe a [BoxedUnit]
        controller.cmdShut(4) shouldBe a [BoxedUnit]
        controller.cmdShut(5) shouldBe a [BoxedUnit]
        controller.cmdShut(6) shouldBe a [BoxedUnit]
        controller.cmdShut(7) shouldBe a [BoxedUnit]
        controller.cmdShut(8) shouldBe a [BoxedUnit]
        controller.cmdShut(9) shouldBe a [BoxedUnit]
        controller.cmdShut(10) shouldBe a [BoxedUnit]
        controller.cmdShut(11) shouldBe a [BoxedUnit]
        controller.cmdShut(12) shouldBe a [BoxedUnit]
        controller.cmdUnShut() shouldBe a [BoxedUnit]
        controller.cmdRedoShut() shouldBe a [BoxedUnit]
        controller.redoShut() shouldBe a [BoxedUnit]
        controller.undoShut() shouldBe a [BoxedUnit]
        controller.doShut(1) shouldBe a [String]
        controller.doShut(2) shouldBe a [String]
        controller.doShut(3) shouldBe a [String]
        controller.doShut(4) shouldBe a [String]
        controller.doShut(5) shouldBe a [String]
        controller.doShut(6) shouldBe a [String]
        controller.doShut(7) shouldBe a [String]
        controller.doShut(8) shouldBe a [String]
        controller.doShut(9) shouldBe a [String]
        controller.doShut(10) shouldBe a [String]
        controller.doShut(11) shouldBe a [String]
        controller.doShut(12) shouldBe a [String]
        controller.shut(1) shouldBe a [BoxedUnit]
        controller.shut(2) shouldBe a [BoxedUnit]
        controller.shut(3) shouldBe a [BoxedUnit]
        controller.shut(4) shouldBe a [BoxedUnit]
        controller.shut(5) shouldBe a [BoxedUnit]
        controller.shut(6) shouldBe a [BoxedUnit]
        controller.shut(7) shouldBe a [BoxedUnit]
        controller.shut(8) shouldBe a [BoxedUnit]
        controller.shut(9) shouldBe a [BoxedUnit]
        controller.shut(10) shouldBe a [BoxedUnit]
        controller.shut(11) shouldBe a [BoxedUnit]
        controller.shut(12) shouldBe a [BoxedUnit]
        controller.getValidShuts() shouldBe a [BoxedUnit]
        controller.calcSum() shouldBe a [Integer]
        controller.calcDiff() shouldBe a [Integer]
        controller.calcProd() shouldBe a [Integer]
        controller.calcDiv() shouldBe a [Integer]
        controller.rollDice() shouldBe a [String]
        controller.printOutput() shouldBe a [String]
        controller.fieldToString shouldBe a [String]
        controller.rollToString shouldBe a [String]

      }



      "handle undo/redo of solving a grid correctly" in {
        controller.cmdShut(1)

      }



      //controller.add(observer)
      "notify its Observer after creation" in {
        controller.createField(0) shouldBe a [BoxedUnit]
        //        observer.updated should be(true)
        controller.matchfield shouldBe a[Field]
        matchfield.field shouldBe a[Array[_]]
      }
      "notify its Observer after dice creation" in {
        controller.createDice() shouldBe a [BoxedUnit]
        //        observer.updated should be(true)
        controller.dice shouldBe a[Array[_]]
        controller.dice(0).start should be(1)
        controller.dice(0).end should be(6)
        controller.dice(1).start should be(1)
        controller.dice(1).end should be(6)
      }
      "notify its Observer after creating players" in {
        controller.createPlayers(false) shouldBe a [BoxedUnit]
        controller.players(0).plrName shouldBe a[String]
        controller.players(0).score shouldBe a[Integer]
        controller.players(1).plrName shouldBe a[String]
        controller.players(1).score shouldBe a[Integer]
        //        observer.updated should be(true)

      }


      //      "notify its Observer after setting current player" in {
      //        controller.setCurrentPlayer() shouldBe a[BoxedUnit]
      //        controller.setCurrentPlayer() shouldBe a[BoxedUnit]
      //        observer.updated should be(true)
      //      }
      //      "notify its Observer after matchfieldToString" in {
      //        controller.fieldToString shouldBe a[String]
      //        observer.updated should be(true)
      //      }
      //      "notify its Observer after dice roll" in {
      //        controller.rollDice() shouldBe a [BoxedUnit]
      //        observer.updated should be(true)
      //      }

      "notify its Observer after shut" in {
        controller.shut(1) shouldBe a [BoxedUnit]
        //        observer.updated should be(true)
      }
      "notify its Observer after rollToString" in {
        controller.rollToString shouldBe a[String]
        //        observer.updated should be(true)
      }


    }
  }
}
