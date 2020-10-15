package de.htwg.se.shutthebox.aview
import de.htwg.se.shutthebox.aview._
import de.htwg.se.shutthebox.aview.gui.{IngamePanel, SwingGUI}
import de.htwg.se.shutthebox.controller._
import de.htwg.se.shutthebox.controller.controllerComponent.ControllerInterface
import de.htwg.se.shutthebox.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.shutthebox.model._
import de.htwg.se.shutthebox.model.fieldComponent.fieldBaseImpl.{Die, Field}
import de.htwg.se.shutthebox.model.playerComponent.playerImpl.Player
import javax.swing.ImageIcon
import org.scalatest._

import scala.swing._
import de.htwg.se.shutthebox.controller.controllerComponent.controllerBaseImpl.Controller
import javax.swing.ImageIcon

import scala.runtime.BoxedUnit


class IngamePanelTest extends WordSpec with Matchers {

  val controller = new Controller()
  controller.createField(0)
  controller.createPlayers(true)
  var gui = new SwingGUI(controller)
  var ingamepnl = new IngamePanel(gui)

  "A IngamePanel" should {
    "a value" in {
      ingamepnl.controller shouldBe a [ControllerInterface]
      ingamepnl.lbl_plr shouldBe a [Label]

      ingamepnl.nextPlayer shouldBe a [BoxedUnit]
      ingamepnl.updateMatchfield() shouldBe a [BoxedUnit]
      ingamepnl.textures shouldBe a [Array[_]]
      ingamepnl.diceTextures shouldBe a [Array[_]]
    }
  }
}


