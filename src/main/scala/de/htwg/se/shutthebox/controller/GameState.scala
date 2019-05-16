package de.htwg.se.shutthebox.controller

object GameState extends Enumeration {
  type GameState = Value
  val MENU, INGAME, ROLL, SHUT = Value

  val map = Map[GameState, String](
    MENU -> "",
    INGAME -> "ingame",
    ROLL -> "Player can roll",
    SHUT -> "Player can shut")

    def message(gameState: GameState) = {
      map(gameState)
    }
}
