package de.htwg.se.shutthebox.aview.gui

import java.io.File

import javax.swing.border.EmptyBorder

import scala.swing._
import scala.swing.event.ButtonClicked

class NewGamePanel(mainFrame:SwingGUI) extends GridPanel(5,1) {

  vGap = 10
  border = new EmptyBorder(40, 80, 20,80)
  preferredSize = new Dimension(1024, 768)

  var controller = mainFrame.ref_controller
  listenTo(controller)

  override def paintComponent(g: Graphics2D): Unit = {
    g.drawImage(mainFrame.resizedTexture("textures" + File.separator + "background.png", mainFrame.size.width-16, mainFrame.size.height-48).getImage(), 0, 0, null)
  }

  var lbl_placeholder = new Label

  var lbl_newGame = new Label {
    icon = mainFrame.resizedTexture("textures" + File.separator + "newGameHeader.png", 780, 148)
  }

  var btn_1vs1 = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures" + File.separator + "1VS1.png", 278, 116)
  }
  var btn_1vsAI = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures" + File.separator + "1VSAi.png", 278, 116)
  }
  var btn_back = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures" + File.separator + "Back.png", 269, 78)
  }
  var btn_bigField = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures" + File.separator + "bigmatchfield_uncheck.png", 800, 113)
    var activated = false
  }

  var btn_sound = new Button {
    opaque = false
    contentAreaFilled = false
    focusPainted = false
    borderPainted = false
    icon = mainFrame.resizedTexture("textures" + File.separator + "Sound_on.png", 68, 48)
    var activated = true
  }

  var pnl_modeSelect = new BorderPanel() {
    opaque = false
    add(btn_1vs1, BorderPanel.Position.West)
    add(btn_1vsAI, BorderPanel.Position.East)
  }

  var pnl_backSound = new BorderPanel() {
    opaque = false
    add(btn_back, BorderPanel.Position.Center)
    add(btn_sound, BorderPanel.Position.South)
  }

  contents += lbl_newGame
  contents += lbl_placeholder
  contents += pnl_modeSelect
  contents += btn_bigField
  contents += pnl_backSound

  listenTo(btn_1vs1)
  listenTo(btn_1vsAI)
  listenTo(btn_back)
  listenTo(btn_bigField)
  listenTo(btn_sound)

  reactions += {
    case ButtonClicked(b) if b == btn_back =>
      mainFrame.contents = mainFrame.mainMenuPanel
      mainFrame.repaint()
  }

  reactions += {
    case ButtonClicked(b) if b == btn_1vs1 =>
      if (!btn_bigField.activated)
        controller.startGame(0, false)
      else
        controller.startGame(1, false)
      mainFrame.contents = new IngamePanel(mainFrame)
      mainFrame.repaint()

    case ButtonClicked(b) if b == btn_1vsAI =>
      if (!btn_bigField.activated)
        controller.startGame(0, true)
      else
        controller.startGame(1, true)
      mainFrame.contents = new IngamePanel(mainFrame)
      mainFrame.repaint()

    case ButtonClicked(b) if b == btn_bigField =>
      if (!btn_bigField.activated) {
        btn_bigField.activated = true
        btn_bigField.icon = mainFrame.resizedTexture("textures" + File.separator + "bigmatchfield_check.png", 800, 113)
      } else {
        btn_bigField.activated = false
        btn_bigField.icon = mainFrame.resizedTexture("textures" + File.separator + "bigmatchfield_uncheck.png", 800, 113)
      }

    case ButtonClicked(b) if b == btn_sound =>
      if (!btn_sound.activated) {
        btn_sound.activated = true
        btn_sound.icon = mainFrame.resizedTexture("textures" + File.separator + "Sound_on.png", 68, 48)
      } else {
        btn_sound.activated = false
        btn_sound.icon = mainFrame.resizedTexture("textures" + File.separator + "Sound_off.png", 68, 48)
      }
  }
}
