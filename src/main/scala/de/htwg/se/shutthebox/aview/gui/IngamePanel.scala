package de.htwg.se.shutthebox.aview.gui

import java.awt.Color

import scala.swing._
import scala.swing.event.ButtonClicked
import de.htwg.se.shutthebox.controller._

class IngamePanel(mainFrame:SwingGUI) extends GridBagPanel {

  preferredSize = new Dimension(800, 600)

  var controller = mainFrame.ref_controller
  listenTo(controller)

  // Quelle : http://otfried.org/scala/index_42.html
  def constraints(x: Int, y: Int,
                  gridwidth: Int = 1, gridheight: Int = 1,
                  weightx: Double = 0.0, weighty: Double = 0.0,
                  fill: GridBagPanel.Fill.Value = GridBagPanel.Fill.None)
  : Constraints = {
    val c = new Constraints
    c.gridx = x
    c.gridy = y
    c.gridwidth = gridwidth
    c.gridheight = gridheight
    c.weightx = weightx
    c.weighty = weighty
    c.fill = fill
    c
  }

  def updateScore(): Unit = {
    lbl_score.text = "Score: " + controller.getScore()
    mainFrame.repaint()
  }

  def nextPlayer(): Unit = {
    updateScore()
    updateMatchfield()
    mainFrame.repaint()
    controller.setCurrentPlayer()
  }

  def updateMatchfield(): Unit = {
    for (i <- 0 to controller.matchfield.field.size-1) {
      if (controller.matchfield.field(i).isShut) {
        numButtons(i).text = " "
        numLabels(i).text = (i+1).toString
      }
      else {
        numButtons(i).text = (i+1).toString
        numLabels(i).text = " "
      }
    }
    mainFrame.repaint()
  }

  var numButtons = Array.ofDim[Button](12)
  for (i <- 1 to 12) {
    numButtons(i - 1) = new Button(i.toString())
    listenTo(numButtons(i-1))
  }
  var numLabels = Array.ofDim[Label](12)
  for (i <- 1 to 12) {
    numLabels(i - 1) = new Label(" ")
  }
  var btn_roll = new Button("Roll Dice")
  listenTo(btn_roll)

  var lbl_dice = new Label(" ") {border=Swing.EtchedBorder(Swing.Lowered) }

  var lbl_score = new Label("Score: " + controller.getScore()) {border=Swing.EtchedBorder(Swing.Lowered) }

  var lbl_message = new Label(" ") {border=Swing.EtchedBorder(Swing.Lowered) }
  lbl_message.foreground = Color.RED
  var btn_nextPlr = new Button("NEXT PLAYER")
  listenTo(btn_nextPlr)

  add(new Label("Player: " + controller.currentPlayer.plrName) {border=Swing.EtchedBorder(Swing.Lowered) },
    constraints(0, 0, gridwidth=12, gridheight= 1, fill=GridBagPanel.Fill.Horizontal))

  for (i <- 1 to controller.matchfield.field.size) {
    add(numButtons(i-1), constraints(i-1, 4))
    add(new Label("----------"), constraints(i-1, 5))
    add(numLabels(i-1), constraints(i-1, 6, gridheight=3))
  }

  add(lbl_score, constraints(0, 11, gridwidth=12, fill=GridBagPanel.Fill.Horizontal))
  add(btn_roll, constraints(0, 12,gridwidth=12, fill=GridBagPanel.Fill.Horizontal))
  add(lbl_dice, constraints(0, 13, gridwidth=12, fill=GridBagPanel.Fill.Horizontal))
  add(lbl_message, constraints(0, 14, gridwidth=12, fill=GridBagPanel.Fill.Horizontal))
  add(btn_nextPlr, constraints(0, 15, gridwidth=12, fill=GridBagPanel.Fill.Horizontal))
  

  reactions += {
    case ButtonClicked(b) if b == numButtons(0) =>
      lbl_message.text = controller.doShut(1)
      //numLabels(0).text = "1"
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(1) =>
      lbl_message.text = controller.doShut(2)
      //numLabels(1).text = "2"
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(2) =>
      lbl_message.text = controller.doShut(3)
      //numLabels(2).text = "3"
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(3) =>
      lbl_message.text = controller.doShut(4)
      //numLabels(3).text = "4"
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(4) =>
      lbl_message.text = controller.doShut(5)
      //numLabels(4).text = "5"
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(5) =>
      lbl_message.text = controller.doShut(6)
      //numLabels(5).text = "6"
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(6) =>
      lbl_message.text = controller.doShut(7)
      //numLabels(6).text = "7"
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(7) =>
      lbl_message.text = controller.doShut(8)
      //numLabels(7).text = "8"
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(8) =>
      lbl_message.text = controller.doShut(9)
      //numLabels(8).text = "9"
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(10) =>
      lbl_message.text = controller.doShut(11)
      //numLabels(10).text = "11"
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(11) =>
      lbl_message.text = controller.doShut(12)
      //numLabels(11).text = "12"
      mainFrame.repaint()
  }

  reactions += {
    case ButtonClicked(b) if b == btn_roll =>
      lbl_message.text = controller.rollDice()
      mainFrame.repaint()

    case ButtonClicked(b) if b == btn_nextPlr =>
      nextPlayer()
  }

  reactions += {
    case event: DiceRolled => lbl_dice.text = controller.dice(0).value + ", " + controller.dice(1).value
    case event: CellShut => updateMatchfield()
                            updateScore()
    case event: CurrentPlayerSet => updateMatchfield()
    case event: Undone => updateMatchfield()
    case event: Redone => updateMatchfield()
    case event: ShowScoreBoard => mainFrame.contents = new ScoreboardPanel(mainFrame)
  }
}