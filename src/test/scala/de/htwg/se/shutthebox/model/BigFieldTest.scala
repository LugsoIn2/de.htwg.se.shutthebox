package de.htwg.se.shutthebox.model

import de.htwg.se.shutthebox.model.fieldComponent.fieldBaseImpl.Cell
import de.htwg.se.shutthebox.model.fieldComponent.fieldAdvancedImpl.BigField
import org.scalatest.{Matchers, WordSpec}

class BigFieldTest extends WordSpec with Matchers{

  val field = new BigField()

  "A Field" should {
    "a value" in {
      field shouldBe a [BigField]
      field.field shouldBe a [Array[_]]
      field.shut(1, field) shouldBe a [Cell]
      field.toString shouldBe a [String]
    }
  }

}
