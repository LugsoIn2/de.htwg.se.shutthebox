package de.htwg.se.shutthebox.controller

import org.scalatest._
import de.htwg.se.shutthebox.util._
import de.htwg.se.shutthebox.model._

import scala.runtime.BoxedUnit

class ControllerTest extends WordSpec with Matchers {
  "A Controller" when {
    "observed by an Observer" should {
      val matchfield = new Field()
      val dice = Array(new Die, new Die)
      val controller = new Controller(matchfield, dice)
      val observer = new Observer {
        var updated: Boolean = false

        def isUpdated: Boolean = updated

        override def update: Unit = updated = true
      }
      controller.add(observer)
      "notify its Observer after creation" in {
        controller.createField() shouldBe a [BoxedUnit]
        observer.updated should be(true)
        controller.matchfield shouldBe a[Field]
        matchfield.field shouldBe a[Array[Cell]]
      }
      "notify its Observer after dice creation" in {
        controller.createDice() shouldBe a [BoxedUnit]
        observer.updated should be(true)
        controller.dice shouldBe a[Array[Die]]
        controller.dice(0).start should be(1)
        controller.dice(0).end should be(6)
        controller.dice(1).start should be(1)
        controller.dice(1).end should be(6)
      }
      "notify its Observer after creating players" in {
        controller.createPlayers() shouldBe a [BoxedUnit]
        controller.players(0).plrName shouldBe a[String]
        controller.players(0).score shouldBe a[Integer]
        controller.players(1).plrName shouldBe a[String]
        controller.players(1).score shouldBe a[Integer]
        observer.updated should be(true)

      }


      "notify its Observer after setting current player" in {
        controller.setCurrentPlayer() shouldBe a[BoxedUnit]
        controller.setCurrentPlayer() shouldBe a[BoxedUnit]
        observer.updated should be(true)
      }
      "notify its Observer after matchfieldToString" in {
        controller.fieldToString shouldBe a[String]
        observer.updated should be(true)
      }
      "notify its Observer after dice roll" in {
        controller.rollDice() shouldBe a [BoxedUnit]
        observer.updated should be(true)
      }
      "a value" in {
        controller.startGame() shouldBe a [BoxedUnit]
        controller.doShut(1) shouldBe a [BoxedUnit]
        controller.doShut(2) shouldBe a [BoxedUnit]
        controller.doShut(3) shouldBe a [BoxedUnit]
        controller.doShut(4) shouldBe a [BoxedUnit]
        controller.doShut(5) shouldBe a [BoxedUnit]
        controller.doShut(6) shouldBe a [BoxedUnit]
        controller.doShut(7) shouldBe a [BoxedUnit]
        controller.doShut(8) shouldBe a [BoxedUnit]
        controller.doShut(9) shouldBe a [BoxedUnit]
        controller.calcSum() shouldBe a [Integer]
        controller.calcDiff() shouldBe a [Integer]
        controller.calcProd() shouldBe a [Integer]
        controller.calcDiv() shouldBe a [Integer]
        controller.printOutput() shouldBe a [String]
        controller.getValidShuts() shouldBe a [BoxedUnit]
        controller.getPlayers() shouldBe a [Array[Player]]
        controller.createPlayers() shouldBe a [BoxedUnit]
      }
      "notify its Observer after shut" in {
        controller.shut(1) shouldBe a [BoxedUnit]
        observer.updated should be(true)
      }
      "notify its Observer after rollToString" in {
        controller.rollToString shouldBe a[String]
        observer.updated should be(true)
      }


    }
  }
}
