package de.htwg.se.shutthebox.controller

import de.htwg.se.shutthebox.util.Command

class SetCommand(value:Int, controller:Controller) extends Command {
  override def doStep: Unit = controller.doShut(value)
  override def undoStep: Unit = controller.undoShut()
  override def redoStep: Unit = controller.redoShut()
}
