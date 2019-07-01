package de.htwg.se.shutthebox.aview.gui

import de.htwg.se.shutthebox.controller.controllerComponent.ControllerInterface

import scala.swing._
import de.htwg.se.shutthebox.controller.controllerComponent.controllerBaseImpl.Controller
import javax.swing.ImageIcon


class SwingGUI(controller : ControllerInterface) extends Frame {
  listenTo(controller)

  peer.setDefaultCloseOperation(3)


  var itemLoad = new MenuItem(new Action("Load") {
    def apply {
      controller.load
    }
  })

  var itemSave = new MenuItem(new Action("Save") {
    def apply {
      controller.save
    }
  })

  var itemExit = new MenuItem(new Action("Exit") {
    def apply {
      System.exit(0)
    }
  })


  def resizedTexture(path: String, width: Int, height: Int): ImageIcon = {
    var imageIcon = new ImageIcon(path)
    var image = imageIcon.getImage(); // transform it
    var newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
    imageIcon = new ImageIcon(newimg); // transform it back
    imageIcon
  }

  var ref_controller = controller
  title = "HTWG - SHUT THE BOX"
  size = new Dimension(1024, 768)
  this.centerOnScreen()
  background = java.awt.Color.white
  var mainMenuPanel = new MainMenuPanel(this)
  contents = mainMenuPanel
  visible = true

  menuBar = new MenuBar {
    contents += new Menu("Game") {
      contents += itemLoad

      contents += itemSave

      contents += itemExit
    }
    visible = true
  }
  makeMenuItemsInvisible()

  def makeMenuItemsInvisible() : Unit = {
    itemLoad.enabled = false
    itemSave.enabled = false
  }

  def makeMenuItemsVisible() : Unit = {
    itemLoad.enabled = true
    itemSave.enabled = true
  }
}