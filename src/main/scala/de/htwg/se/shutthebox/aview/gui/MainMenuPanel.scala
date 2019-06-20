package de.htwg.se.shutthebox.aview.gui


import javax.swing.border.EmptyBorder

import scala.swing._
import scala.swing.event.ButtonClicked

class MainMenuPanel(mainFrame:SwingGUI) extends GridPanel(5,1) {

  vGap = 10
  border = new EmptyBorder(40, 80, 20,80)
  preferredSize = new Dimension(1024, 768)

  override def paintComponent(g: Graphics2D): Unit = {
    g.drawImage(mainFrame.resizedTexture("textures\\background.png", mainFrame.size.width-16, mainFrame.size.height-48).getImage(), 0, 0, null)
  }

  var lbl_placeholder = new Label

  var lbl_shutTheBox = new Label {
    icon = mainFrame.resizedTexture("textures\\header.png", 780, 148)
  }

  var btn_newGame = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures\\newGame.png", 442, 100)
  }

  var btn_rules = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures\\howToPlay.png", 360, 90)
  }
  var btn_quit = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures\\quit.png", 268, 78)
  }
  var pnl_rules = new RulesPanel(mainFrame)
  var pnl_newGame = new NewGamePanel(mainFrame)

  contents += lbl_shutTheBox
  contents += lbl_placeholder
  contents += btn_newGame
  contents += btn_rules
  contents += btn_quit

  listenTo(btn_newGame)
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


