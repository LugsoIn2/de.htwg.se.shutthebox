package de.htwg.se.shutthebox.aview
import de.htwg.se.shutthebox.aview._
import de.htwg.se.shutthebox.controller._
import de.htwg.se.shutthebox.model._
import org.scalatest._


class TUITest extends WordSpec with Matchers {
  val matchfield = new Field()
  val dice = Array.ofDim[Die](2)
  val players = Array.ofDim[Player](2)
  val controller = new Controller(matchfield, dice)
  val tui = new TUI(controller)

  "A TUI" should {
    "a value" in {
      tui shouldBe a [TUI]
      //tui.matchfield shouldBe a [Field]
      tui.printHeader() shouldBe a [String]
      tui.nextPlayer() shouldBe a [Player]
      tui.printStartGame() shouldBe a [String]
      //tui.printDice(1) shouldBe a [String]
      tui.printRules() shouldBe a [String]
      //tui.startGame() shouldBe a [Player]
      tui.processInputLine("input", dice) shouldBe a [String]
    }
  }
}
