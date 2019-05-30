package de.htwg.se.shutthebox
import de.htwg.se.shutthebox.aview._
import scala.io.StdIn.readLine
import de.htwg.se.shutthebox.model._
import de.htwg.se.shutthebox.controller.Controller
import de.htwg.se.shutthebox.aview.gui.SwingGUI
import javax.swing.SwingUtilities


object ShutTheBox {

  val controller = new Controller()
  val tui = new TUI(controller)
  val gui = new SwingGUI(controller)

  def main(args: Array[String]): Unit = {


    val name = "ShutTheBox"
    var input = ""
    do {
      input = readLine()
      tui.processInputLine(input, controller.dice)
    }while (input != "q")
  }
}






