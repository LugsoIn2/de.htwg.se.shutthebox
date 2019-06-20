package de.htwg.se.shutthebox.aview
import de.htwg.se.shutthebox.aview._
import de.htwg.se.shutthebox.controller._
import de.htwg.se.shutthebox.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.shutthebox.model._
import org.scalatest._

import scala.runtime.BoxedUnit


class TUITest extends WordSpec with Matchers {
  val matchfield = new Field()
  val dice:Array[Die] = Array.ofDim[Die](2)
  val players:Array[Player] = Array.ofDim[Player](2)
  val controller = new Controller()
  val tui = new TUI(controller)

  "A TUI" should {
    "a value" in {
      tui shouldBe a [TUI]
      tui.printHeader() shouldBe a [String]
      //tui.nextPlayer() shouldBe a [BoxedUnit]
      tui.printStartGame() shouldBe a [String]
      tui.printRules() shouldBe a [String]
      tui.processInputLine("input", dice) shouldBe a [String]
    }
  }
}


