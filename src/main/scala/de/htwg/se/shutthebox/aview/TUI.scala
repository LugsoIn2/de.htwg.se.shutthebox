package de.htwg.se.shutthebox.aview
import de.htwg.se.shutthebox._
import de.htwg.se.shutthebox.controller.Controller
import de.htwg.se.shutthebox.model._

//class TUI(field:Field, players:Array[Player], currentPlr:Player) {
class TUI(controller:Controller) {
  var matchfield = controller.createField()
  print(printHeader())



  def processInputLine(input: String, dice: Array[Die]): String = {

    input match {
      case "s" => startGame()
      case "q" => System.exit(0)
      case "r" => print(printDice(dice(0).roll) + printDice(dice(1).roll))
      case "n" => print(nextPlayer())
      case "h" => print(printRules())
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
    input
  }

  def startGame(): Player = {
    matchfield = controller.createField()
    var players = controller.createPlayers()
    print(printStartGame())
    players(0).setName(1)   // problems with code coverage
    players(1).setName(2)   // NullPointerException or infinite loop for input
    nextPlayer()
  }

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
    if (controller.getCurrentPlayerIndex == 0) {
      controller.setCurrentPlayer(1)
    } else if (controller.getCurrentPlayerIndex == 1){
      controller.setCurrentPlayer(0)
    } else {
      controller.setCurrentPlayer(0)
    }
    print("NEXT PLAYERS TURN!")
    controller.getCurrentPlayer()
  }

  def printDice(value:Int) : String = {
    value match {
      case 1 =>
        """
          |╔═════════╗
          |║         ║
          |║    O    ║
          |║         ║
          |╚═════════╝
          |""".stripMargin
      case 2 =>
        """
          |╔═════════╗
          |║ O       ║
          |║         ║
          |║       O ║
          |╚═════════╝
          |""".stripMargin
      case 3 =>
        """
          |╔═════════╗
          |║ O       ║
          |║    O    ║
          |║       O ║
          |╚═════════╝
          |""".stripMargin
      case 4 =>
        """
          |╔═════════╗
          |║ O     O ║
          |║         ║
          |║ O     O ║
          |╚═════════╝
          |""".stripMargin
      case 5 =>
        """
          |╔═════════╗
          |║ O     O ║
          |║    O    ║
          |║ O     O ║
          |╚═════════╝
          |""".stripMargin
      case 6 =>
        """
          |╔═════════╗
          |║ O     O ║
          |║ O     O ║
          |║ O     O ║
          |╚═════════╝
          |""".stripMargin
    }
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


}