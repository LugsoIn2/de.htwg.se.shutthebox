package de.htwg.se.shutthebox.model

// Field, which implements 9 cells in List
class Field {
  val field = Array.ofDim[Cell](9)
  for (i <- 1 to 9) {
    field(i - 1) = new Cell()
    field(i - 1).value = i
  }
}
