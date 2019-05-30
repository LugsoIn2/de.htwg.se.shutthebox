package de.htwg.se.shutthebox.model

import de.htwg.se.shutthebox.controller.Controller
import util.control.Breaks._

class AI(controller:Controller) extends Player {

  var validShuts = Array.ofDim[Int](4)
  var singleShuts = Array.ofDim[Int](2)

  override def setName(index:Integer) : String = {
    printf("Player %d: ",index)
    plrName = "AI"//readLine()
    plrName
  }

  def randomTimeMillis(min:Int, max:Int) : Int = {
    var value = min + scala.util.Random.nextInt((max - min) + 1)
    value
  }

  def aiRoll(): Unit = {
    controller.rollDice()
  }

  def getValidShuts() : Unit = {
    validShuts = Array(controller.validSum, controller.validDiff, controller.validProd, controller.validDiv)
    singleShuts = controller.validNumber
  }

  def giveUp() : Unit = {
    println("That's it! :-(")
    Thread.sleep(2000)
    controller.setCurrentPlayer()
  }

  def analyze() : Unit = {
    println("AI is thinking")
    Thread.sleep(randomTimeMillis(500, 2000))

    var currentMax = validShuts.max
    var currentMaxIndex = validShuts.indexOf(validShuts.max)

   // if maximum is in range of matchfield
    if (currentMax > 0 && currentMax <= controller.matchfield.field.size ) {
      // if the cell isn't already shut
      if (!controller.matchfield.field(currentMax-1).isShut) {
        controller.doShut(validShuts.max)
        think()
      } else {
        validShuts(currentMaxIndex) = 0
        // if the two single dice values aren't already shut
        if (!controller.matchfield.field(singleShuts(0)-1).isShut && !controller.matchfield.field(singleShuts(1)-1).isShut) {
          // shut the single die values
          controller.doShut(singleShuts(0))
          Thread.sleep(700) // so human player can follow along
          controller.doShut(singleShuts(1))
          think()
        }
        else if(!controller.matchfield.field(singleShuts(0)-1).isShut | !controller.matchfield.field(singleShuts(1)-1).isShut) {
          analyze()
        } else {
          giveUp()
        }
      }
    } else {
      validShuts(currentMaxIndex) = 0 // set current max to 0, if result > matchfield
      analyze()
    }
  }


  def think(): Unit = {
    Thread.sleep(randomTimeMillis(500, 2000))
    controller.rollDice()
    Thread.sleep(randomTimeMillis(500, 2000))
    getValidShuts()
    analyze()
  }


}
