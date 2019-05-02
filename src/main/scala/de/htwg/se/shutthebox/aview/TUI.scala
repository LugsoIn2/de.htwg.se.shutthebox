package de.htwg.se.shutthebox.aview
import de.htwg.se.shutthebox._
import de.htwg.se.shutthebox.controller.Controller
import de.htwg.se.shutthebox.model._

//class TUI(field:Field, players:Array[Player], currentPlr:Player) {
class TUI(controller:Controller) {
  var matchfield = controller.createField()
  println(" ==== SHUT THE BOX ====")
  println("Press \"s\" to START the game!")
  println("Press \"n\" for next player")
  println("Press \"r\" to ROLL dice")
  println("Press \"q\" to QUIT game")
  println("Press \"h\" for HELP")
  println("Press numbers (1 - 9) to shut the cells")


  def processInputLine(input: String, dice: Array[Die]): Unit = {

    input match {
      case "s" => startGame()
      case "q" => System.exit(0)
      case "r" => printf("Die1: %d%n",dice(0).roll)
                  printf("Die2: %d%n",dice(1).roll)
      //case "n" => nextPlayer()
      case "1" => matchfield.shut(1, matchfield)//shut(1,matchfield)
      case "2" => matchfield.shut(2, matchfield)//shut(2,matchfield)
      case "3" => matchfield.shut(3, matchfield)
      case "4" => matchfield.shut(4, matchfield)
      case "5" => matchfield.shut(5, matchfield)
      case "6" => matchfield.shut(6, matchfield)
      case "7" => matchfield.shut(7, matchfield)
      case "8" => matchfield.shut(8, matchfield)
      case "9" => matchfield.shut(9, matchfield)
      case _ => println("")
    }
  }

  def startGame(): Unit = {
    //matchfield = controller.createField()
    var players = controller.createPlayers()
    var currentPlayer = players(0)
    println("---- NEW GAME ----")
    println()
    println("Please enter your names!")
    print("Player 1: ")
    players(0).inputName()
    println()
    print("Player 2: ")
    players(1).inputName()
    println()
    println(currentPlayer.plrName + ": IT'S YOUR TURN!")
  }



}
