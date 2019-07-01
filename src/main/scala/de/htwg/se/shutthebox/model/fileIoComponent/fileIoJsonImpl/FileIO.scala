package de.htwg.se.shutthebox.model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.shutthebox.ShutTheBoxModule
import de.htwg.se.shutthebox.model.fieldComponent.{cellInterface, fieldInterface}
import de.htwg.se.shutthebox.model.fileIoComponent.FileIOInterface

import scala.io.Source
import play.api.libs.json._


class FileIO extends FileIOInterface {

  override def load: fieldInterface = {
    var field: fieldInterface = null
    val source: String = Source.fromFile("field.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val matchfieldSize = (json \ "field" \ "size").get.toString.toInt
    val cells = (json \ "field" \ "cells").get.toString
    val injector = Guice.createInjector(new ShutTheBoxModule)
    matchfieldSize match {
      case 9 => field = injector.instance[fieldInterface](Names.named("normal"))
      case 12 => field = injector.instance[fieldInterface](Names.named("big"))
      case _ =>
    }
    val celltest = (json \ "field" \\ "cells") (0).toString()
    var jsonList: List[JsValue] = Json.parse(celltest).as[List[JsValue]]
    var count = 0
    for (feld <- jsonList) {
      if (feld.toString().contains("true")) {
        field.field(count).isShut = true
      }
      count += 1
    }
    field
  }


  override def save(field: fieldInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("field.json"))
    pw.write(Json.prettyPrint(fieldToJson(field)))
    pw.close
  }


  implicit val cellWrites = new Writes[cellInterface] {
    def writes(cell: cellInterface) = Json.obj(
      "value" -> cell.value,
    )
  }


  def fieldToJson(field: fieldInterface) = {
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

}
