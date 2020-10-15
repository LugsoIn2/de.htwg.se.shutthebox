package de.htwg.se.shutthebox.aview.gui

import java.awt.Color
import java.io.File

import javax.swing.border.EmptyBorder

import scala.swing._
import scala.swing.event.ButtonClicked

class RulesPanel(mainFrame:SwingGUI) extends GridPanel(3,1) {
  var controller = mainFrame.ref_controller

  vGap = 10
  border = new EmptyBorder(0, 80, 20,80)
  preferredSize = new Dimension(1024, 768)

  var courierFont = new Font("Courier New", 1, 36)

  override def paintComponent(g: Graphics2D): Unit = {
    g.drawImage(mainFrame.resizedTexture("textures" + File.separator + "background.png", mainFrame.size.width-16, mainFrame.size.height-48).getImage(), 0, 0, null)
  }

  var lbl_header = new Label {
    icon = mainFrame.resizedTexture("textures" + File.separator + "howToPlay_header.png", 780, 148)
  }

  var rulesText =
    """
      | Shut the Box can be played by any number of players although it is
      | most enjoyable with two, three or four.
      | Some people even play the game solo as a pastime akin to patience.
      | As played traditionally in English pubs, Shut the Box is a gambling
      | pastime with each of the players paying an agreed amount into the
      | "pool" at the beginning and the winner collecting the pool at the end
      | of each round. However, it isn't necessary to gamble in order to play
      | the game.
      |
      |A round of the game consists of each player taking one turn.
      |A player's takes a turn by repeatedly throwing the dice until the player
      |cannot continue. Each throw of the dice is taken as follows:
      |
      |If 7, 8 and 9 are all covered, the player decides whether to throw
      |one die or two. If any of these 3 numbers are still uncovered, the
      |player must use both dice. The player throws the die or dice into
      |the box and adds up the pips.
      |The player must then cover up a set of unique uncovered numbers that
      |add up to the sum thrown.
      |So for instance, if the total pips is 8, the player may choose one of
      |the following sets of numbers as long as all of the numbers in the set
      |are available to be covered:
      |
      |8
      |7 & 1
      |6 & 2
      |5 & 3
      |5 & 2 & 1
      |4 & 3 & 1
      |The player then does exactly the same thing with a second throw and so on.
      |
      |Once a number is covered up, it stays covered so, eventually, the player will
      |throw a total for which it is not possible to find a set of uncovered numbers.
      |When this happens, the player scores the sum of the numbers that are still uncovered.
      |So if the numbers 1, 5 and 9 are uncovered and the player
      |throws a 4, with options 4 or 3 & 1, the turn finishes and the player's score is 15.
      |
      |If anyone succeeds in shutting the box  i.e. closing all the numbers, that player wins outright
      |immediately and receives double the stake from all players.
      |Otherwise, after each player has taken one turn, the winner of the round is the player with the
      |lowest score.
    """.stripMargin

  var scrllPne_rules = new ScrollPane {
    var lbl_rules = new Label() {
      text = convertToMultiline(rulesText)
      font = new Font("Courier New", 1, 20)
      foreground = Color.WHITE
      opaque = true
      background = new Color(59, 124, 108)
    }
    contents = lbl_rules
  }
  var btn_back = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures" + File.separator + "Back.png", 269, 78)
  }

  def convertToMultiline(s:String): String = {
    return "<html>" + s.replaceAll("\n", "<br>")
  }

  contents += lbl_header
  contents += scrllPne_rules
  contents += btn_back

  listenTo(btn_back)

  reactions += {
    case ButtonClicked(b) if b == btn_back =>
      mainFrame.contents = mainFrame.mainMenuPanel
      mainFrame.repaint()
  }
}