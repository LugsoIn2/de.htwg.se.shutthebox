package de.htwg.se.shutthebox.model.fieldComponent

import de.htwg.se.shutthebox.model.cellComponent.cellBaseImpl.Cell

trait fieldInterface {
  def field : Array[Cell]
  def shut(number:Integer, field:fieldInterface) : Cell
  def toString : String
}

