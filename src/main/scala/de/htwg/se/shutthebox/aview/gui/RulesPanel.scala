package de.htwg.se.shutthebox.aview.gui

import scala.swing._
import scala.swing.event.ButtonClicked

class RulesPanel(mainFrame:SwingGUI) extends GridPanel(3,1) {
  preferredSize = new Dimension(800, 600)


  var lbl_rules = new Label {
    text = "??? HOW TO PLAY ???"
    font = new Font("Verdana", 1, 64)
    preferredSize = new Dimension(800, 128)
  }
  var txtbx_rules = new TextPane {
    text = "Hallo\n Das ist ein Test!"
    font = new Font("Verdana", 1, 18)
    preferredSize = new Dimension(800, 472)
  }
  var btn_back = new Button {
    text = "\uD83E\uDC78 go back"
    preferredSize = new Dimension(64, 128)
    visible = true
  }

  contents += lbl_rules
  contents += txtbx_rules
  contents += btn_back

  listenTo(btn_back)

  reactions += {
    case ButtonClicked(b) if b == btn_back =>
      mainFrame.contents = mainFrame.mainMenuPanel
      mainFrame.repaint()
  }


}
