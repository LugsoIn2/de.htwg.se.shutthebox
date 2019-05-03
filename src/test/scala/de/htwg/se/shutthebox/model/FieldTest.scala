package de.htwg.se.shutthebox.model

import org.scalatest._

class FieldTest extends WordSpec with Matchers {

  val field = new Field()


  "A Field" should {
    "a value" in {
      field shouldBe a [Field]
      field.field shouldBe a [Array[Cell]]
    }
  }

}
