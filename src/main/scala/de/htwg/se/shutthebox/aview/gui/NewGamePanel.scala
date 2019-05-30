package de.htwg.se.shutthebox.aview.gui

import scala.swing._
import scala.swing.event.ButtonClicked

class NewGamePanel(mainFrame:SwingGUI) extends GridPanel(5,1) {

  var controller = mainFrame.ref_controller

  var lbl_newGame = new Label {
    text = "New Game"
    font = new Font("Verdana", 1, 72)
    preferredSize = new Dimension(800, 128)
  }

  var btn_1vs1 = new Button {
    text = "1 VS 1"
    preferredSize = new Dimension(64, 128)
    visible = true
  }
  var btn_1vsAI = new Button {
    text = "1 VS AI"
    preferredSize = new Dimension(64, 128)
    visible = true
  }
  var btn_back = new Button {
    text = "\uD83E\uDC78 go back"
    preferredSize = new Dimension(64, 128)
    visible = true
  }
  var chkbx_bigField = new CheckBox {
    text = "Big field?"
    preferredSize = new Dimension(64, 128)
    visible = true
  }


  contents += lbl_newGame
  contents += btn_1vs1
  contents += btn_1vsAI
  contents += chkbx_bigField
  contents += btn_back

  listenTo(btn_1vs1)
  listenTo(btn_1vsAI)
  listenTo(btn_back)
  listenTo(chkbx_bigField)

  reactions += {
    case ButtonClicked(b) if b == btn_back =>
      mainFrame.contents = mainFrame.mainMenuPanel
      mainFrame.repaint()
  }

  reactions += {
    case ButtonClicked(b) if b == btn_1vs1 =>
      if (!chkbx_bigField.selected)
        controller.startGame(0, false)
      else
        controller.startGame(1, false)
      mainFrame.contents = new IngamePanel(mainFrame)
      mainFrame.repaint()

    case ButtonClicked(b) if b == btn_1vsAI =>
      if (!chkbx_bigField.selected)
        controller.startGame(0, true)
      else
        controller.startGame(1, true)
      mainFrame.contents = new IngamePanel(mainFrame)
      mainFrame.repaint()
  }

}
