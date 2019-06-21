package de.htwg.se.shutthebox.model.fieldComponent

import de.htwg.se.shutthebox.model.Cell

abstract class AbstractField {
  def field : Array[Cell]
  def shut(number:Integer, field:AbstractField) : Cell
  override def toString : String
}

