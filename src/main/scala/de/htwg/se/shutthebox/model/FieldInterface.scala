package de.htwg.se.shutthebox.model

trait FieldInterface {
  def shut(number:Integer, field:Field) : Cell
  override def toString : String
}

