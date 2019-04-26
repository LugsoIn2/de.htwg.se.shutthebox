package de.htwg.se.shutthebox.aview
import de.htwg.se.shutthebox._
import de.htwg.se.shutthebox.model._

class TUI(field:Field, players:Array[Player], currentPlr:Player) {
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
      case "1" => shut(1,field)
      case "2" => shut(2,field)
      case "3" => shut(3,field)
      case "4" => shut(4,field)
      case "5" => shut(5,field)
      case "6" => shut(6,field)
      case "7" => shut(7,field)
      case "8" => shut(8,field)
      case "9" => shut(9,field)
      case _ => println("")
    }
  }

  def startGame(): Unit = {
    println("---- NEW GAME ----")
    println()
    println("Please enter your names!")
    print("Player 1: ")
    players(0).inputName()
    println()
    print("Player 2: ")
    players(1).inputName()
    println()
    println(currentPlr.plrName + ": IT'S YOUR TURN!")
  }

  //def nextPlayer(): Unit = {
    //if (current)
    //currentPlr = players(1)
  //}

  def shut(number:Integer, field:Field) : Unit = {
    if (!field.field(number-1).isShut) {
      field.field(number-1).isShut = true;
    } else {
      println("This cell is already shut!")
    }

    println()
    println("|=============================================|")
    print(" ")
    for (i <- 0 to 8) {
      if (!field.field(i).isShut) {
        print(i + 1 + "    ")
      } else {
        print("     ")
      }
    }
    println()
    println("|---------------------------------------------|")
    print(" ")
    for (i <- 0 to 8) {
        if (field.field(i).isShut) {
          print(i + 1 + "    ")
        } else {
          print("     ")
        }
    }
    println()
    println("|=============================================|")
  }
}
