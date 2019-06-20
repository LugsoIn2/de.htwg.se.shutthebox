package de.htwg.se.shutthebox.aview.gui

import java.awt.Color
import scala.swing._
import scala.swing.event.ButtonClicked
import de.htwg.se.shutthebox.controller.controllerComponent._
import javax.swing.border.EmptyBorder
import javax.swing.{BorderFactory, ImageIcon}


class IngamePanel(mainFrame:SwingGUI) extends GridPanel(6,1) {

  preferredSize = new Dimension(1024, 768)
  border = new EmptyBorder(20, 20, 20,20)

  override def paintComponent(g: Graphics2D): Unit = {
    g.drawImage(mainFrame.resizedTexture("textures\\bgIngame.png", mainFrame.size.width-16, mainFrame.size.height-48).getImage(), 0, 0, null)
  }

  var controller = mainFrame.ref_controller
  listenTo(controller)

  var textures = Array.ofDim[ImageIcon](26)
  for (i <- 0 to 11) {
    textures(i) = mainFrame.resizedTexture("textures\\cell_" + (i+1) + ".png", 32, 64)
  }
  for (i <- 12 to 23) {
    textures(i) = mainFrame.resizedTexture("textures\\cell_shut_" + (i-11) + ".png", 32, 64)
  }
  textures(24) = mainFrame.resizedTexture("textures\\cell_placeholder.png", 32, 64)

  var diceTextures = Array.ofDim[ImageIcon](6)
  for (i <- 1 to 6) {
    diceTextures(i-1) = mainFrame.resizedTexture("textures\\Dice" + i + ".png",105, 105)
  }


  var lbl_plr = new Label {
    icon = mainFrame.resizedTexture("textures\\Player.png", 147, 33)
    horizontalTextPosition = Alignment.Center
    verticalTextPosition = Alignment.Center
    font = new Font("Courier New", 1, 20)
    foreground = Color.WHITE
    text = controller.currentPlayer.plrName
  }
  var lbl_score_header = new Label {
    icon = mainFrame.resizedTexture("textures\\SCORE.png", 274, 32)
  }
  var lbl_score = new Label {
    icon = mainFrame.resizedTexture("textures\\ScoreNum.png", 90, 32)
    horizontalTextPosition = Alignment.Center
    verticalTextPosition = Alignment.Center
    font = new Font("Courier New", 1, 30)
    foreground = Color.WHITE
    text = controller.getScore().toString
  }
  var lbl_placeholder = new Label

  var btn_undo = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures\\undo.png", 38, 33)
  }
  var btn_redo = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures\\redo.png", 38, 33)
  }

  var pnl_top_undoRedo = new GridPanel(1,2) {
    opaque = false
    contents += btn_undo
    contents += btn_redo
  }

  var pnl_top = new GridPanel(0,3) {
    opaque = false
    contents += lbl_plr
    contents += lbl_score_header
    contents += pnl_top_undoRedo
    contents += lbl_placeholder
    contents += lbl_score
  }

  var numButtons = Array.ofDim[Button](12)
  for (i <- 1 to 12) {
    numButtons(i - 1) = new Button()
    numButtons(i - 1).opaque = false
    numButtons(i - 1).contentAreaFilled = false
    numButtons(i - 1).focusPainted = false
    numButtons(i - 1).borderPainted = false
    numButtons(i - 1).icon = mainFrame.resizedTexture("textures\\cell_" + i + ".png",32,64)
    listenTo(numButtons(i-1))
  }
  var numLabels = Array.ofDim[Label](12)
  var imageIcon = new ImageIcon("textures\\cell_placeholder.png")
  var image = imageIcon.getImage(); // transform it
  var newimg = image.getScaledInstance(32, 64,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
  imageIcon = new ImageIcon(newimg);  // transform it back
  for (i <- 1 to 12) {
    numLabels(i - 1) = new Label()
    numButtons(i - 1).opaque = false
    numLabels(i - 1).icon = imageIcon
  }

  var pnl_matchfield_cells = new GridPanel(1,0) {
    opaque = false
    for(i <- 0 to controller.matchfield.field.size-1) {
      contents += numButtons(i)
    }
  }

  var pnl_matchfield_shutcells = new GridPanel(1,0) {
    opaque = false
    for(i <- 0 to controller.matchfield.field.size-1) {
      contents += numLabels(i)
    }
  }

  var pnl_matchfield = new GridPanel(0,1) {
    border = BorderFactory.createLineBorder(new Color(0, 63, 45), 3, true)
    opaque = false
    contents += pnl_matchfield_cells
    contents += pnl_matchfield_shutcells
  }

  var lbl_die1 = new Label {
    icon = diceTextures(0)
  }
  var lbl_die2 = new Label {
    icon = diceTextures(0)
  }
  var btn_roll = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures\\rollbutton.png", 160, 95)
  }

  var pnl_dice = new GridPanel(1,3) {
    opaque = false
    contents += lbl_die1
    contents += lbl_die2
  }

  var btn_quit = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures\\back_ingame.png", 112, 33)
  }

  var btn_sound = new Button {
    opaque = false;
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures\\Sound_on.png", 68, 48)
    var activated = true
  }

  var pnl_bottom = new BorderPanel {
    opaque = false
    add(btn_quit, BorderPanel.Position.West)
    add(btn_sound, BorderPanel.Position.East)
  }



  def updateScore(): Unit = {
    lbl_score.text = controller.getScore().toString
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
        numButtons(i).icon = textures(24)
        //numButtons(i).text = " "
        //numLabels(i).icon = resizedTexture("textures\\cell_shut_" + (i+1) + ".png", 32, 64)
        numLabels(i).icon = textures(i+12)
        //numLabels(i).text = (i+1).toString
      }
      else {
        //numButtons(i).icon = resizedTexture("textures\\cell_" + (i+1) + ".png", 32, 64)
        numButtons(i).icon = textures(i)
        //numLabels(i).icon = resizedTexture("textures\\cell_placeholder.png", 32, 64)
        numLabels(i).icon = textures(24)
      }
    }
    lbl_message.text = ""
    lbl_plr.text = controller.currentPlayer.plrName
    mainFrame.repaint()
  }


  listenTo(btn_roll)
  listenTo(btn_sound)
  listenTo(btn_undo)
  listenTo(btn_redo)
  listenTo(btn_quit)


  var lbl_message = new Label(" ") {
    opaque = false
    foreground = new Color(250, 56, 60)
    font = new Font("Courier New", 1, 20)
  }


  var btn_nextPlr = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures\\nextplayer.png", 185, 38)
  }
  listenTo(btn_nextPlr)

  var pnl_message_nextPlr = new BorderPanel {
    opaque = false
    add(lbl_message, BorderPanel.Position.Center)
    add(btn_nextPlr, BorderPanel.Position.South)
  }

  contents += pnl_top
  contents += pnl_matchfield
  contents += pnl_dice
  contents += btn_roll
  contents += pnl_message_nextPlr
  contents += pnl_bottom


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

    case ButtonClicked(b) if b == btn_undo =>
      controller.undoShut()

    case ButtonClicked(b) if b == btn_redo =>
      controller.redoShut()

    case ButtonClicked(b) if b == btn_nextPlr =>
      nextPlayer()

    case ButtonClicked(b) if b == btn_sound =>
      if (!btn_sound.activated) {
        btn_sound.activated = true
        btn_sound.icon = mainFrame.resizedTexture("textures\\Sound_on.png", 68, 48)
      } else {
        btn_sound.activated = false
        btn_sound.icon = mainFrame.resizedTexture("textures\\Sound_off.png", 68, 48)
      }

    case ButtonClicked(b) if b == btn_quit =>
      mainFrame.contents = mainFrame.mainMenuPanel
      mainFrame.repaint()
  }

  reactions += {
    //case event: DiceRolled => lbl_dice.text = controller.dice(0).value + ", " + controller.dice(1).value
    case event: DiceRolled => lbl_die1.icon = diceTextures(controller.dice(0).value-1)
                              lbl_die2.icon = diceTextures(controller.dice(1).value-1)
    case event: CellShut => updateMatchfield()
                            updateScore()
    case event: CurrentPlayerSet => updateMatchfield()
    case event: Undone => updateMatchfield()
    case event: Redone => updateMatchfield()
    case event: ShowScoreBoard => mainFrame.contents = new ScoreboardPanel(mainFrame)
  }
}