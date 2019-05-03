package de.htwg.se.shutthebox.model

// Field, which implements 9 cells in List
class Field {
  val field = Array.ofDim[Cell](9)
  for (i <- 1 to 9) {
    field(i - 1) = new Cell()
    field(i - 1).value = i
  }

  def shut(number:Integer, field:Field) : Cell = {
    if (!field.field(number-1).isShut) {
      field.field(number-1).isShut = true;
    } else {
      println("This cell is already shut!")
    }

    print("\n|=============================================|\n ")
    for (i <- 0 to 8) {
      if (!field.field(i).isShut) {
        print(i + 1 + "    ")
      } else {
        print("     ")
      }
    }
    print("\n|---------------------------------------------|\n ")
    for (i <- 0 to 8) {
      if (field.field(i).isShut) {
        print(i + 1 + "    ")
      } else {
        print("     ")
      }
    }
    print("\n|=============================================|\n")
    field.field(number-1)
  }
}
