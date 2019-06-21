package de.htwg.se.shutthebox.model

import de.htwg.se.shutthebox.model.cellComponent.cellBaseImpl.Cell
import org.scalatest._

class CellTest extends WordSpec with Matchers {
  val cell = new Cell()

  "A Cell" should {
    "a value" in {
      cell shouldBe a [Cell]
      cell.value should be >= 1
      cell.value should be <= 9
      cell.isShut should be(false)
    }
  }


}
