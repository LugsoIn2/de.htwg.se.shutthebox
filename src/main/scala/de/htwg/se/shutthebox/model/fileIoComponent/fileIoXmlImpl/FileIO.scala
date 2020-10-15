package de.htwg.se.shutthebox.model.fileIoComponent.fileIoXmlImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.shutthebox.ShutTheBoxModule
import de.htwg.se.shutthebox.model.fileIoComponent.FileIOInterface
import de.htwg.se.shutthebox.model.fieldComponent.fieldInterface
import play.api.libs.json.{JsNumber, JsValue, Json}
import scala.xml._

import scala.io.Source
import scala.xml.{NodeSeq, PrettyPrinter}


class FileIO extends FileIOInterface {

  override def load: fieldInterface = {
    var field: fieldInterface = null
    var file = scala.xml.XML.loadFile("field.xml")
    val matchfieldSizeAttr = (file \ "field" \ "@size")
    val matchfieldSize = matchfieldSizeAttr.text.toInt
    val cellsAttr = (file \ "field" \ "@cells")
    val cells = cellsAttr.text.toString
    val injector = Guice.createInjector(new ShutTheBoxModule)
    matchfieldSize match {
      case 9 => field = injector.instance[fieldInterface](Names.named("normal"))
      case 12 => field = injector.instance[fieldInterface](Names.named("big"))
      case _ =>
    }
    val celltest = (file \ "field" \\ "@cells") (0).toString()
    print(celltest)
    //var xmlList: List[Node] = Xml.parse(celltest).as[List[Node]]
    //var count = 0
    //for (feld <- jsonList) {
      //if (feld.toString().contains("true")) {
        //field.field(count).isShut = true
      //}
      //count += 1
    //}
    field
  }

  def fieldToXml(field: fieldInterface) = {
    Json.obj(
      "field" -> Json.obj(
        "size" -> JsNumber(field.field.size),
        "cells" -> Json.toJson(
          for {
            index <- 0 until field.field.size
          } yield {
            Json.obj(
              index.toString -> Json.toJson(field.field(index).isShut));
            //"cell" -> Json.toJson(field.field(index).value))

          }
        )
      )
    )
  }

  override def save(field: fieldInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("field.xml"))
    pw.write(Json.prettyPrint(fieldToXml(field)))
    pw.close
  }
}
