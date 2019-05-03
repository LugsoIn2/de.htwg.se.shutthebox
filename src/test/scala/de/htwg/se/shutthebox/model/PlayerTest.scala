package de.htwg.se.shutthebox.model

import org.scalatest._

class PlayerTest extends WordSpec with Matchers {

  val player = new Player()

  "A Player" should {
    "a value" in {
      player.plrName shouldBe a [String]
      player.score shouldBe a [Integer]
    }
  }

}
