package de.htwg.se.shutthebox.model

import scala.collection.mutable.ListBuffer

object ShutTheBox {
  def main(args: Array[String]): Unit = {
    val name = "ShutTheBox"
    val spielfeld = new Field

  }
}

// Field, which implements 9 cells in List
class Field {
  val field = Array.ofDim[Cell](9)
  for (i <- 1 to 9) {
    field(i) = new Cell()
    field(i).value = i
  }
}

class Cell() {
  var value = 1;
  var isShut = false;
}

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
