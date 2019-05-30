package de.htwg.se.shutthebox.aview.gui

import scala.swing._
import scala.swing.event.ButtonClicked

class MainMenuPanel(mainFrame:SwingGUI) extends GridPanel(5,1) {

  preferredSize = new Dimension(800, 600)

  var lbl_shutTheBox = new Label {
    text = "SHUT THE BOX"
    font = new Font("Verdana", 1, 72)
    preferredSize = new Dimension(800, 128)
  }

  var btn_newGame = new Button {
    text = "New Game"
    preferredSize = new Dimension(64, 128)
    visible = true
  }
  var btn_options = new Button {
    text = "Options"
    preferredSize = new Dimension(64, 128)
    visible = true
  }
  var btn_rules = new Button {
    text = "How to play"
    preferredSize = new Dimension(64, 128)
    visible = true
  }
  var btn_quit = new Button {
    text = "Quit Game"
    preferredSize = new Dimension(64, 128)
    visible = true
  }
  var pnl_rules = new RulesPanel(mainFrame)
  var pnl_newGame = new NewGamePanel(mainFrame)

  contents += lbl_shutTheBox
  contents += btn_newGame
  contents += btn_options
  contents += btn_rules
  contents += btn_quit

  listenTo(btn_newGame)
  listenTo(btn_options)
  listenTo(btn_rules)
  listenTo(btn_quit)

  reactions += {
    case ButtonClicked(b) if b == btn_quit =>
      System.exit(0)
  }

  reactions += {
    case ButtonClicked(b) if b == btn_rules =>
      mainFrame.contents = pnl_rules
      mainFrame.repaint()
  }

  reactions += {
    case ButtonClicked(b) if b == btn_newGame =>
      mainFrame.contents = pnl_newGame
      mainFrame.repaint()
  }
}


