package de.htwg.se.shutthebox.model

object ShutTheBox {
  def main(args: Array[String]): Unit = {
    val program = "ShutTheBox"
    println(program)
  }

  object Field {
    val field1 = (Array.ofDim[Cell](9))
    for (i <- 0 to 8) {
      field1(i) = new Cell
      field1(i).value = i + 1

    }
  }

  class Cell() {
    var value = 0
    val isShut = false;
  }
}

object Testobject {
  val testtest = "Hallo du"
  println(testtest)
}
