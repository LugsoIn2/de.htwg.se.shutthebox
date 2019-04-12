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
    field(i) = new Cell(i)
  }
}

class Cell(val x : Integer) {
  var value = x
  var isShut = false;
}

class Die() {
  val rnd = scala.util.Random
  val start = 1
  val end   = 6
  var value = roll

  def roll: Integer = {
    start + rnd.nextInt((end - start) + 1)
  }
}
