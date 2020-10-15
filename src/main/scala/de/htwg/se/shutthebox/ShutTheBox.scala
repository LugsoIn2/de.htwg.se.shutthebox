package de.htwg.se.shutthebox
import de.htwg.se.shutthebox.aview._


import com.google.inject.Guice
import de.htwg.se.shutthebox.aview.TUI
import scala.io.StdIn.readLine
import de.htwg.se.shutthebox.aview.gui.SwingGUI
import de.htwg.se.shutthebox.controller.controllerComponent.ControllerInterface


object ShutTheBox {

  val injector = Guice.createInjector(new ShutTheBoxModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new TUI(controller)
  val gui = new SwingGUI(controller)

  def main(args: Array[String]): Unit = {


    val name = "ShutTheBox"
    var input = ""
    do {
      input = readLine()
      tui.processInputLine(input)
    }while (input != "q")
  }
}






