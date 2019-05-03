package de.htwg.se.shutthebox.model

import java.io.ByteArrayInputStream

import org.scalatest._

class PlayerTest extends WordSpec with Matchers {

  val player = new Player()

  "A Player" should {
    "a value" in {
      player.plrName shouldBe a[String]
      player.score shouldBe a [Integer]
      player.setName(0) shouldBe a [String]
      player.setName(1) shouldBe a [String]
      player.updateScore(1) shouldBe a [Integer]
    }
  }
}
