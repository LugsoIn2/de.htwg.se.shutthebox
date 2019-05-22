package de.htwg.se.shutthebox.aview
import de.htwg.se.shutthebox._
import de.htwg.se.shutthebox.controller.{Controller, GameState}
import de.htwg.se.shutthebox.model._
import de.htwg.se.shutthebox.util.Observer

//class TUI(field:Field, players:Array[Player], currentPlr:Player) {
class TUI(controller:Controller) extends Observer {
  controller.add(this)
  print(printHeader())



  def processInputLine(input: String, dice: Array[Die]): String = {


      input match {
        case "ss" => controller.startGame(0)
        case "sb" => controller.startGame(1)
        case "z" => controller.cmdUnShut() // Undo
                    controller.printOutput()
        case "y" => controller.cmdRedoShut() // Redo

        case "q" => System.exit(0)
        case "r" => controller.rollDice()
        case "n" => controller.setCurrentPlayer()
        case "h" => print(printRules())
        case "1" => controller.cmdShut(1)
        case "2" => controller.cmdShut(2)
        case "3" => controller.cmdShut(3)
        case "4" => controller.cmdShut(4)
        case "5" => controller.cmdShut(5)
        case "6" => controller.cmdShut(6)
        case "7" => controller.cmdShut(7)
        case "8" => controller.cmdShut(8)
        case "9" => controller.cmdShut(9)
        case "10" => if (controller.getField().isInstanceOf[BigField])
                        controller.cmdShut(10)
        case "11" => if (controller.getField().isInstanceOf[BigField])
                        controller.cmdShut(11)
        case "12" => if (controller.getField().isInstanceOf[BigField])
                        controller.cmdShut(12)
        case default => ""
      }
      input
  }

  /*def startGame(): Player = {
    controller.createField()
    var players = controller.createPlayers()
    print(printStartGame())
    controller.getPlayers()(0).setName(1)   // problems with code coverage
    controller.getPlayers()(1).setName(2)   // NullPointerException or infinite loop for input
    nextPlayer()
  }*/

  def printHeader() : String = {
    """
      |╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
      |║  ______   ___   ___   __  __   _________   _________  ___   ___   ______        _______   ______   __     __      ║
      |║ /_____/\ /__/\ /__/\ /_/\/_/\ /________/\ /________/\/__/\ /__/\ /_____/\     /_______/\ /_____/\ /__/\ /__/\     ║
      |║ \::::_\/_\::\ \\  \ \\:\ \:\ \\__.::.__\/ \__.::.__\/\::\ \\  \ \\::::_\/_    \::: _  \ \\:::_ \ \\ \::\\:.\ \    ║
      |║  \:\/___/\\::\/_\ .\ \\:\ \:\ \  \::\ \      \::\ \   \::\/_\ .\ \\:\/___/\    \::(_)  \/_\:\ \ \ \\_\::_\:_\/    ║
      |║   \_::._\:\\:: ___::\ \\:\ \:\ \  \::\ \      \::\ \   \:: ___::\ \\::___\/_    \::  _  \ \\:\ \ \ \ _\/__\_\_/\  ║
      |║     /____\:\\: \ \\::\ \\:\_\:\ \  \::\ \      \::\ \   \: \ \\::\ \\:\____/\    \::(_)  \ \\:\_\ \ \\ \ \ \::\ \ ║
      |║     \_____\/ \__\/ \::\/ \_____\/   \__\/       \__\/    \__\/ \::\/ \_____\/     \_______\/ \_____\/ \_\/  \__\/ ║
      |║                                                                                                                   ║
      |║                                          Press "s" to START the game!                                             ║
      |║                                           Press "n" for next player                                               ║
      |║                                             Press "r" to ROLL dice                                                ║
      |║                                             Press "q" to QUIT game                                                ║
      |║                                               Press "h" for HELP                                                  ║
      |║                                      Press numbers (1 - 9) to shut the cells                                      ║
      |╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
      |""".stripMargin
  }

  def printStartGame() : String = {
    """
      |---- NEW GAME ----
      |Please enter your names!
      |""".stripMargin
  }

  def nextPlayer(): Player = {
    controller.setCurrentPlayer()
    print("NEXT PLAYERS TURN!")
    controller.getCurrentPlayer()
  }


  def printRules() : String = {
    """
      |'SHUT THE BOX' ist ein einfaches, aber sehr verlockendes Würfelspiel.
      |Es kann als nettes Trinkspiel oder als kleines Gesellschaftsspiel für zwischendurch gespielt werden.
      |
      |Es besteht aus einem Würfelbrett mit 9 schwenkbaren Klappen,
      |beschrieben mit den Ziffern 1 bis 9 und zwei Würfeln.
      |Zu Beginn des Spiels werden die Klappen in senkrechte Position gebracht.
      |Ziel ist es, alle Klappen umzuschwenken, um so eine möglichst geringe Teamsumme zu erreichen.
      |Das Umschwenken ist durch Würfeln und die damit erzielten Augenzahlen möglich.
      |
      |In dieser Version wird mit zwei Teams gespielt. TEAM 1 ist an der Reihe und muss würfeln.
      |Es dürfen durch das Drücken der Tasten 1-9 auf der Tastatur folgende Spielsteine umgedreht werden:
      |
      |- Die Summe aus beiden Würfeln
      |- Die Differenz der beiden Würfel (grösserer Wert minus kleinerer Wert)
      |- Das Produkt der Würfel
      |- Die Division beider Würfel (grösserer Wert geteilt durch kleineren Wert und ohne Rest)
      |- Die einzelnen Werte beider Würfel
      |
      |Nach jedem Umschwenken muss mit der LEERTASTE neu gewürfelt werden.
      |
      |Wenn das Team etwas würfelt, das in allen oben genannten Fällen schon umgeklappt ist,
      |ist das TEAM 1 mit seinem Spielzug fertig und darf nicht mehr würfeln.
      |
      |Nun muss die Taste [ n ] gedrückt werden und TEAM 2 darf spielen.
      |
      |Ziel des Spiels ist es, eine NIEDRIGERE Teamsumme zu erreichen als das Gegnerteam.
      |Somit hat das Team mit der höheren Punktzahl verloren und muss einen Kurzen trinken.
      |Danach geht das Ganze von vorne los. :-)
      |""".stripMargin
  }

  override def update: Unit = {
    print(controller.printOutput())
  }
}
