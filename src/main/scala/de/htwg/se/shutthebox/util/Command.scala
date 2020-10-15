package de.htwg.se.shutthebox.util

trait Command {
  def doStep:Unit
  def undoStep:Unit
  def redoStep:Unit
}
