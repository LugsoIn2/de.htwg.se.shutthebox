package de.htwg.se.shutthebox.model

import org.scalatest._

class DieTest extends WordSpec with Matchers{
  val die = new Die()

  "A Die" should {
    "a value" in {
      die.start should be (1)
      die.end should be (6)
      die.roll shouldBe a [Integer]
      die.value should be >= 1
      die.value should be <= 6
      die.toString shouldBe a [String]

    }
  }


}
