package de.htwg.se.shutthebox.controller

object GameState extends Enumeration {
  type GameState = Value
  val MENU, INGAME, ROLLDICE, SHUT = Value

  val map = Map[GameState, String](
    MENU -> "",
    INGAME -> "ingame",
    //STARTGAME -> "ingame",
    ROLLDICE -> "Player can roll",
    SHUT -> "Player can shut")

    def message(gameState: GameState) = {
      map(gameState)
    }
}
