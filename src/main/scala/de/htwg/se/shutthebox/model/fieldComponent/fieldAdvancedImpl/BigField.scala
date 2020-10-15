package de.htwg.se.shutthebox.model.fieldComponent.fieldAdvancedImpl

import de.htwg.se.shutthebox.model.fieldComponent.fieldBaseImpl.Cell
import de.htwg.se.shutthebox.model.fieldComponent.fieldInterface

class BigField extends fieldInterface {


  override val field:Array[Cell] = Array.ofDim[Cell](12)
  for (i <- 1 to 12) {
    field(i - 1) = new Cell()
    field(i - 1).value = i
  }

  override def shut(number:Integer, field:fieldInterface) : Cell = {
    if (!field.field(number-1).isShut) {
      field.field(number-1).isShut = true
    }
    field.field(number-1)
  }


  override def toString : String = {
    var output = ""
    output += "\n|==============================================================|\n "
    for (i <- 0 to 11) {
      if (!field(i).isShut) {
        output += i + 1 + "    "
      } else {
        output += "     "
      }
    }
    output += "\n|--------------------------------------------------------------|\n "
    for (i <- 0 to 11) {
      if (field(i).isShut) {
        output += i + 1 + "    "
      } else {
        output += "     "
      }
    }
    output += "\n|==============================================================|\n"

    output
  }

}
