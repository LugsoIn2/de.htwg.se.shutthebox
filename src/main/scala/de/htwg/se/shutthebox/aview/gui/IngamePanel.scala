package de.htwg.se.shutthebox.aview.gui

import java.awt.Color
import java.io.File

import scala.swing._
import scala.swing.event.ButtonClicked
import de.htwg.se.shutthebox.controller.controllerComponent._
import de.htwg.se.shutthebox.controller.controllerComponent.controllerBaseImpl.Controller
import javax.swing.border.EmptyBorder
import javax.swing.{BorderFactory, ImageIcon}


class IngamePanel(mainFrame:SwingGUI) extends GridPanel(6,1) {

  preferredSize = new Dimension(1024, 768)
  border = new EmptyBorder(20, 20, 20,20)

  override def paintComponent(g: Graphics2D): Unit = {
    g.drawImage(mainFrame.resizedTexture("textures" + File.separator + "bgIngame.png", mainFrame.size.width-16, mainFrame.size.height-48).getImage, 0, 0, null)
  }

  var controller:ControllerInterface = mainFrame.ref_controller
  listenTo(controller)

  var textures:Array[ImageIcon] = Array.ofDim[ImageIcon](26)
  for (i <- 0 to 11) {
    textures(i) = mainFrame.resizedTexture("textures" + File.separator + "cell_" + (i+1) + ".png", 32, 64)
  }
  for (i <- 12 to 23) {
    textures(i) = mainFrame.resizedTexture("textures" + File.separator + "cell_shut_" + (i-11) + ".png", 32, 64)
  }
  textures(24) = mainFrame.resizedTexture("textures" + File.separator + "cell_placeholder.png", 32, 64)

  var diceTextures:Array[ImageIcon] = Array.ofDim[ImageIcon](6)
  for (i <- 1 to 6) {
    diceTextures(i-1) = mainFrame.resizedTexture("textures" + File.separator + "Dice" + i + ".png",105, 105)
  }


  var lbl_plr:Label = new Label {
    icon = mainFrame.resizedTexture("textures" + File.separator + "Player.png", 147, 33)
    horizontalTextPosition = Alignment.Center
    verticalTextPosition = Alignment.Center
    font = new Font("Courier New", 1, 20)
    foreground = Color.WHITE
    text = controller.currentPlayer.plrName
  }
  var lbl_score_header:Label = new Label {
    icon = mainFrame.resizedTexture("textures" + File.separator + "SCORE.png", 274, 32)
  }
  var lbl_score:Label = new Label {
    icon = mainFrame.resizedTexture("textures" + File.separator + "ScoreNum.png", 90, 32)
    horizontalTextPosition = Alignment.Center
    verticalTextPosition = Alignment.Center
    font = new Font("Courier New", 1, 30)
    foreground = Color.WHITE
    text = controller.getScore.toString
  }
  var lbl_placeholder:Label = new Label

  var btn_undo:Button = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures" + File.separator + "undo.png", 38, 33)
  }
  var btn_redo:Button = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures" + File.separator + "redo.png", 38, 33)
  }

  var pnl_top_undoRedo:GridPanel = new GridPanel(1,2) {
    opaque = false
    contents += btn_undo
    contents += btn_redo
  }

  var pnl_top:GridPanel = new GridPanel(0,3) {
    opaque = false
    contents += lbl_plr
    contents += lbl_score_header
    contents += pnl_top_undoRedo
    contents += lbl_placeholder
    contents += lbl_score
  }

  var numButtons:Array[Button] = Array.ofDim[Button](12)
  for (i <- 1 to 12) {
    numButtons(i - 1) = new Button()
    numButtons(i - 1).opaque = false
    numButtons(i - 1).contentAreaFilled = false
    numButtons(i - 1).focusPainted = false
    numButtons(i - 1).borderPainted = false
    numButtons(i - 1).icon = mainFrame.resizedTexture("textures" + File.separator + "cell_" + i + ".png",32,64)
    listenTo(numButtons(i-1))
  }
  var numLabels:Array[Label] = Array.ofDim[Label](12)
  var imageIcon:ImageIcon = new ImageIcon("textures" + File.separator + "cell_placeholder.png")
  var image:Image = imageIcon.getImage // transform it
  var newimg:Image = image.getScaledInstance(32, 64,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
  imageIcon = new ImageIcon(newimg)  // transform it back
  for (i <- 1 to 12) {
    numLabels(i - 1) = new Label()
    numButtons(i - 1).opaque = false
    numLabels(i - 1).icon = imageIcon
  }

  var pnl_matchfield_cells:GridPanel = new GridPanel(1,0) {
    opaque = false
    for(i <- controller.matchfield.field.indices) {
      contents += numButtons(i)
    }
  }

  var pnl_matchfield_shutcells:GridPanel = new GridPanel(1,0) {
    opaque = false
    for(i <- controller.matchfield.field.indices) {
      contents += numLabels(i)
    }
  }

  var pnl_matchfield:GridPanel = new GridPanel(0,1) {
    border = BorderFactory.createLineBorder(new Color(0, 63, 45), 3, true)
    opaque = false
    contents += pnl_matchfield_cells
    contents += pnl_matchfield_shutcells
  }

  var lbl_die1:Label = new Label {
    icon = diceTextures(0)
  }
  var lbl_die2:Label = new Label {
    icon = diceTextures(0)
  }
  var btn_roll:Button = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures" + File.separator + "rollbutton.png", 160, 95)
  }

  var pnl_dice:GridPanel = new GridPanel(1,3) {
    opaque = false
    contents += lbl_die1
    contents += lbl_die2
  }

  var btn_quit:Button = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures" + File.separator + "back_ingame.png", 112, 33)
  }

  var btn_sound = new ToggleButton {
    var activated = true
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures" + File.separator + "Sound_on.png", 68, 48)

  }

  var pnl_bottom:BorderPanel = new BorderPanel {
    opaque = false
    add(btn_quit, BorderPanel.Position.West)
    add(btn_sound, BorderPanel.Position.East)
  }



  def updateScore(): Unit = {
    lbl_score.text = controller.getScore.toString
    mainFrame.repaint()
  }

  def nextPlayer(): Unit = {
    updateScore()
    updateMatchfield()
    mainFrame.repaint()
    controller.setCurrentPlayer()

  }

  def updateMatchfield(): Unit = {
    for (i <- controller.matchfield.field.indices) {
      if (controller.matchfield.field(i).isShut) {
        numButtons(i).icon = textures(24)
        numLabels(i).icon = textures(i+12)
      }
      else {
        numButtons(i).icon = textures(i)
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


  var lbl_message:Label = new Label(" ") {
    opaque = false
    foreground = new Color(250, 56, 60)
    font = new Font("Courier New", 1, 20)
  }


  var btn_nextPlr:Button = new Button {
    opaque = false
    contentAreaFilled = false
    borderPainted = false
    focusPainted = false
    icon = mainFrame.resizedTexture("textures" + File.separator + "nextplayer.png", 185, 38)
  }
  listenTo(btn_nextPlr)

  var pnl_message_nextPlr:BorderPanel = new BorderPanel {
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
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(1) =>
      lbl_message.text = controller.doShut(2)
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(2) =>
      lbl_message.text = controller.doShut(3)
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(3) =>
      lbl_message.text = controller.doShut(4)
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(4) =>
      lbl_message.text = controller.doShut(5)
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(5) =>
      lbl_message.text = controller.doShut(6)
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(6) =>
      lbl_message.text = controller.doShut(7)
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(7) =>
      lbl_message.text = controller.doShut(8)
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(8) =>
      lbl_message.text = controller.doShut(9)
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(9) =>
      lbl_message.text = controller.doShut(10)
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(10) =>
      lbl_message.text = controller.doShut(11)
      mainFrame.repaint()

    case ButtonClicked(b) if b == numButtons(11) =>
      lbl_message.text = controller.doShut(12)
      mainFrame.repaint()
  }

  reactions += {
    case ButtonClicked(b) if b == btn_roll =>
      lbl_message.text = controller.rollDice
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
        btn_sound.icon = mainFrame.resizedTexture("textures" + File.separator + "Sound_on.png", 68, 48)
      } else {
        btn_sound.activated = false
        btn_sound.icon = mainFrame.resizedTexture("textures" + File.separator + "Sound_off.png", 68, 48)
      }

    case ButtonClicked(b) if b == btn_quit =>
      mainFrame.contents = mainFrame.mainMenuPanel
      mainFrame.repaint()
  }

  reactions += {
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