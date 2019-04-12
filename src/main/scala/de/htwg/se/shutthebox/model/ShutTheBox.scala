package de.htwg.se.shutthebox.model

object ShutTheBox {
  def main(args: Array[String]): Unit = {
    val name = "ShutTheBox"
    val spielfeld = new Field

  }
}


class Field {
  val field = (Array.ofDim[Cell](9))
  for (i <- 0 to 8) {
    field(i) = new Cell
    field(i).value = i + 1
  }
}

class Cell {
  var value = 0
  var isShut = false;
}

