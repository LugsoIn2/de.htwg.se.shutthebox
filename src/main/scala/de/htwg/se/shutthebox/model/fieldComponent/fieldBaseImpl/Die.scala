package de.htwg.se.shutthebox.model.fieldComponent.fieldBaseImpl

import de.htwg.se.shutthebox.model.fieldComponent.dieInterface

//Dice to roll
class Die() extends dieInterface {
  val start = 1
  val end   = 6
  var value = 1

  def roll: Integer = {
     value = start + scala.util.Random.nextInt((end - start) + 1)
     value
  }

  override def toString : String = {
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
}
