package de.htwg.se.shutthebox.aview.gui

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.shutthebox.controller._
import scala.io.Source._

class CellClicked(val row: Int, val column: Int) extends Event

class SwingGUI(controller : Controller) extends Frame {
  listenTo(controller)

  title = "HTWG - SHUT THE BOX"
  size = new Dimension(800, 600)
  this.centerOnScreen()
  resizable = false
  background = java.awt.Color.white


  visible = true
}
