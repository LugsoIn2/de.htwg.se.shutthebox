package de.htwg.se.shutthebox.aview.gui

import scala.swing._

class MainMenuPanel extends GridPanel(5,1) {

  preferredSize = new Dimension(800, 600)

  var label = new Label {
    text = "Main Menu"
    font = new Font("Verdana", 1, 72)
    preferredSize = new Dimension(800, 128)
  }

  var button = new Button {
    text = "New Game"
    preferredSize = new Dimension(64, 128)
    visible = true
  }
  var button_options = new Button {
    text = "Options"
    preferredSize = new Dimension(64, 128)
    visible = true
  }

  contents += label
  contents += button
  contents += button_options
}
