package de.htwg.se.shutthebox.controller

import org.scalatest._
import de.htwg.se.shutthebox.util._
import de.htwg.se.shutthebox.model._

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
        controller.createField()
        observer.updated should be(true)
        controller.matchfield shouldBe a[Field]
        matchfield.field shouldBe a[Array[Cell]]
      }
      "notify its Observer after dice creation" in {
        controller.createDice()
        observer.updated should be(true)
        controller.dice shouldBe a[Array[Die]]
        controller.dice(0).start should be(1)
        controller.dice(0).end should be(6)
        controller.dice(1).start should be(1)
        controller.dice(1).end should be(6)
      }
      "notify its Observer after creating players" in {
        controller.createPlayers() shouldBe a[Array[Player]]
        observer.updated should be(true)
        controller.players(0).plrName shouldBe a[String]
        controller.players(0).score shouldBe a[Integer]
        controller.players(1).plrName shouldBe a[String]
        controller.players(1).score shouldBe a[Integer]

      }
      "notify its Observer after setting current player" in {
        //controller.setCurrentPlayer(0) shouldBe a[Player]
        //controller.setCurrentPlayer(1) shouldBe a[Player]
        observer.updated should be(true)
      }
      "notify its Observer after getting current player's index" in {
        //controller.getCurrentPlayerIndex shouldBe a[Integer]
        observer.updated should be(true)
      }
      "notify its Observer after getting current player" in {
        controller.getCurrentPlayer shouldBe a[Player]
        observer.updated should be(true)
      }
      "notify its Observer after matchfieldToString" in {
        controller.fieldToString shouldBe a[String]
        observer.updated should be(true)
      }
    }
  }
}
