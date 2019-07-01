package de.htwg.se.shutthebox.aview
import de.htwg.se.shutthebox.aview._
import de.htwg.se.shutthebox.controller._
import de.htwg.se.shutthebox.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.shutthebox.model._
import de.htwg.se.shutthebox.model.fieldComponent.fieldBaseImpl.{Die, Field}
import de.htwg.se.shutthebox.model.fileIoComponent.fileIoXmlImpl.FileIO
import de.htwg.se.shutthebox.model.playerComponent.playerImpl.Player
import org.scalatest._
import de.htwg.se.shutthebox.model._
import de.htwg.se.shutthebox.model.fieldComponent.fieldInterface

import scala.runtime.BoxedUnit


class FileIoToXmlImplTest extends WordSpec with Matchers {

  var fileIo = new FileIO()
  var field = new Field()


  "A FileIoToXmlImpl" should {
    "a value" in {
      fileIo.load shouldBe a [fieldInterface]
      fileIo.save(field) shouldBe a [BoxedUnit]
      fileIo.fieldToXml(field) shouldBe a [BoxedUnit]
    }
  }
}


