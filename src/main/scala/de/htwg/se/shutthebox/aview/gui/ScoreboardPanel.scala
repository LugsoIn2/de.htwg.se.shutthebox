package de.htwg.se.shutthebox.aview.gui

import scala.swing._

class ScoreboardPanel(mainFrame:SwingGUI) extends GridPanel(5,1) {

  var controller = mainFrame.ref_controller

  preferredSize = new Dimension(800, 600)

  var lbl_scoreboard = new Label {
    text = "SCOREBOARD"
    font = new Font("Verdana", 1, 72)
    preferredSize = new Dimension(800, 128)
  }

  var lbl_plr1 = new Label {
    text = "Player 1 - " + controller.players(0).plrName + ": " + controller.players(0).score
  }

  var lbl_plr2 = new Label {
    text = "Player 1 - " + controller.players(1).plrName + ": " + controller.players(1).score
  }

  var lbl_winner = new Label {
    if (controller.getPlayers()(0).score < controller.getPlayers()(1).score) {
      text = controller.getPlayers()(0).plrName + " wins!!!"
    } else if (controller.getPlayers()(0).score > controller.getPlayers()(1).score) {
      text = controller.getPlayers()(1).plrName + " wins!!!"
    } else {
      text = "The game ends in a draw! :-("
    }
  }

  contents += lbl_scoreboard
  contents += lbl_plr1
  contents += lbl_plr2
  contents += lbl_winner

}
