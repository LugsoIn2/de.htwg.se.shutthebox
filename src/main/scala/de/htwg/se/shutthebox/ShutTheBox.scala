package de.htwg.se.shutthebox
import de.htwg.se.shutthebox.aview._
import scala.io.StdIn.readLine
import de.htwg.se.shutthebox.model._
import de.htwg.se.shutthebox.controller.Controller

object ShutTheBox {

  val controller = new Controller(new Field, Array(new Die, new Die), Array(new Player, new Player))
  val tui = new TUI(controller)
  controller.notifyObservers


  def main(args: Array[String]): Unit = {
    val name = "ShutTheBox"
    var input = ""
    //var players = Array(new Player, new Player)
    //var currentPlr = controller.players(0)
    //val spielfeld = new Field
    //val dice = Array(new Die, new Die)
    //val tui = new TUI(spielfeld, players, currentPlr)

    do {
      input = readLine()
      tui.processInputLine(input, controller.dice)
    }while (input != "q")
  }
}






