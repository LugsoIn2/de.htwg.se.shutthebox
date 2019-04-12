package de.htwg.se.shutthebox.model

import org.scalatest._

class ShutTheBoxTest extends WordSpec with Matchers {
  val field = new Field()
  val cell = new Cell()

  "A ShutTheBox" should {
    "a value" in {
      
    }
  }

  "A Cell" should {
    "a value" in {
      cell shouldBe a [Cell]
      cell.value should be >= 0
      cell.value should be <= 9
      cell.isShut should be(false)
    }
  }

  "A Field" should {
    "a value" in {
      field shouldBe a [Field]
      field.field shouldBe a [Array[Cell]]
    }
  }
}



