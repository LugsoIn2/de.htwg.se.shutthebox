package de.htwg.se.shutthebox.model.fileIoComponent.fileIoXmlImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.shutthebox.ShutTheBoxModule
import de.htwg.se.shutthebox.model.fileIoComponent.FileIOInterface
import de.htwg.se.shutthebox.model.fieldComponent.fieldInterface
import play.api.libs.json.{JsValue, Json}

import scala.io.Source
import scala.xml.{NodeSeq, PrettyPrinter}


class FileIO extends FileIOInterface {

  override def load: fieldInterface = {
    var field: fieldInterface = null
    var file = scala.xml.XML.loadFile("field.xml")
    var sizeAttr = (file \\ "field" \ "@size")
    val size = sizeAttr.text.toInt
    val injector = Guice.createInjector(new ShutTheBoxModule)
    size match {
      case 9 => field = injector.instance[fieldInterface](Names.named("normal"))
      case 12 => field = injector.instance[fieldInterface](Names.named("big"))
      case _ =>
    }

    /*val cellNodes = (file \\ "cell")
    for (cell <- cellNodes) {
      val value: Int = cell.text.trim.toInt
      val isShut: Boolean = cell.text.trim.toBoolean

      field.field(cell).isShut = isShut

    }*/
    field
  }

  override def save(field: fieldInterface): Unit = ???
}
