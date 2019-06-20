package de.htwg.se.shutthebox.aview.gui

import java.awt.Color

import javax.swing.border.EmptyBorder

import scala.swing._
import scala.swing.event.ButtonClicked

class ScoreboardPanel(mainFrame:SwingGUI) extends GridPanel(5,1) {

  var controller = mainFrame.ref_controller

  vGap = 10
  border = new EmptyBorder(40, 80, 20,80)
  preferredSize = new Dimension(1024, 768)

  var courierFont = new Font("Courier New", 1, 36)

  override def paintComponent(g: Graphics2D): Unit = {
    g.drawImage(mainFrame.resizedTexture("textures\\background.png", mainFrame.size.width-16, mainFrame.size.height-48).getImage(), 0, 0, null)
  }

  var lbl_scoreboard = new Label {
    icon = mainFrame.resizedTexture("textures\\scoreboard_header.png", 780, 148)
  }

  var lbl_plr1 = new Label {
    font = courierFont
    foreground = Color.WHITE
    text = "Player 1 - " + controller.players(0).plrName + ": " + controller.players(0).score
  }

  var lbl_plr2 = new Label {
    font = courierFont
    foreground = Color.WHITE
    text = "Player 2 - " + controller.players(1).plrName + ": " + controller.players(1).score
  }

  var lbl_winner = new Label {
    font = courierFont
    foreground = Color.WHITE
    if (controller.getPlayers()(0).score < controller.getPlayers()(1).score) {
      text = controller.getPlayers()(0).plrName + " wins!!!"
    } else if (controller.getPlayers()(0).score > controller.getPlayers()(1).score) {
      text = controller.getPlayers()(1).plrName + " wins!!!"
    } else {
      text = "The game ends in a draw! :-("
    }
  }

  var btn_back = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures\\Back.png", 269, 78)
  }

  listenTo(btn_back)

  contents += lbl_scoreboard
  contents += lbl_plr1
  contents += lbl_plr2
  contents += lbl_winner
  contents += btn_back

  reactions += {
    case ButtonClicked(b) if b == btn_back =>
      mainFrame.contents = mainFrame.mainMenuPanel
      mainFrame.repaint()
  }

}
