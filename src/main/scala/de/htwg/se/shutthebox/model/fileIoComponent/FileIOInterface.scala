package de.htwg.se.shutthebox.model.fileIoComponent

import de.htwg.se.shutthebox.model.fieldComponent.fieldInterface

trait FileIOInterface {
  def load: fieldInterface
  def save(field:fieldInterface): Unit
}
