package de.htwg.se.shutthebox.model

import org.scalatest._

class ShutTheBoxTest extends WordSpec with Matchers {
    "A Cell" should {
      "a value" in {
        Cell.value should be >= 0
      }
    }
  "A Cell" should {
    "a value" in {
      Cell.value should be <= 9
    }
  }

}
