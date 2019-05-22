package de.htwg.se.shutthebox.model

abstract class AbstractField {
  def field : Array[Cell]
  def shut(number:Integer, field:AbstractField) : Cell
  override def toString : String
}

