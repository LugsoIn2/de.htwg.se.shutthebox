case class Field()

val field1 = (Array.ofDim[Int](9))
for (i <- 0 to 8) {
  field1(i) = i+1;
}

field1



val start = 1
val end   = 6

val rnd = scala.util.Random

val dice1 = start + rnd.nextInt((end - start) + 1)
val dice2 = start + rnd.nextInt((end - start) + 1)




//field1.cells(0)=cell1
//field1.cells(0).x
//field1.cells(0).y