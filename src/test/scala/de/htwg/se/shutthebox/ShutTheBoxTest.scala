package de.htwg.se.shutthebox

import de.htwg.se.shutthebox.model._
import org.scalatest._

class ShutTheBoxTest extends WordSpec with Matchers {
  val field = new Field()
  val cell = new Cell()
  val die = new Die()

  "A ShutTheBox" should {
    "a value" in {
      
    }
  }

  "A Cell" should {
    "a value" in {
      cell shouldBe a [Cell]
      cell.value should be >= 1
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

  "A Die" should {
    "a value" in {
      die.start should be === 1
      die.end should be === 6
      //die.value shouldBe between 1 and 6 ?????
    }
  }

}



