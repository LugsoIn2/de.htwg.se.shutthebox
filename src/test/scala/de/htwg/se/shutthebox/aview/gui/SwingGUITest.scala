package de.htwg.se.shutthebox.aview
import de.htwg.se.shutthebox.aview._
import de.htwg.se.shutthebox.aview.gui.SwingGUI
import de.htwg.se.shutthebox.controller._
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


class SwingGUITest extends WordSpec with Matchers {

  val controller = new Controller()
  var gui = new SwingGUI(controller)

  "A SwingGUI" should {
    "a value" in {
      gui.resizedTexture("", 8, 8) shouldBe a [ImageIcon]
      gui.visible shouldBe true
    }
  }
}


