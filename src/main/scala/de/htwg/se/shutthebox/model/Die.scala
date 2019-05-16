package de.htwg.se.shutthebox.model

//Dice to roll
class Die() {
  val start = 1
  val end   = 6
  var value = 1

  // Function to roll the die, generate random number
  // between 1 and 6
  // save generated number in value variable
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
