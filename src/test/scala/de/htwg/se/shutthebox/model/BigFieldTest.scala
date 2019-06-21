package de.htwg.se.shutthebox.model

import de.htwg.se.shutthebox.model.fieldComponent.fieldBaseImpl.BigField
import org.scalatest.{Matchers, WordSpec}

class BigFieldTest extends WordSpec with Matchers{

  val field = new BigField()

  "A Field" should {
    "a value" in {
      field shouldBe a [BigField]
      field.field shouldBe a [Array[Cell]]
      field.shut(1, field) shouldBe a [Cell]
      field.toString shouldBe a [String]
    }
  }

}
