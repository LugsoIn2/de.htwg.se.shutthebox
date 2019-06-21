package de.htwg.se.shutthebox.model.fieldComponent

import de.htwg.se.shutthebox.model.fieldComponent.fieldBaseImpl.Cell

trait fieldInterface {
  def field : Array[Cell]
  def shut(number:Integer, field:fieldInterface) : Cell
  def toString : String
}

