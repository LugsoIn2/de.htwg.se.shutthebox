package de.htwg.se.shutthebox.model

//Dice to roll
class Die() {
  val start = 1
  val end   = 6
  var value = roll

  // Function to roll the die, generate random number
  // between 1 and 6
  // save generated number in value variable
  def roll: Integer = {
    start + scala.util.Random.nextInt((end - start) + 1)
  }
}
