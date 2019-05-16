package de.htwg.se.shutthebox.controller

object ShutState extends Enumeration {
  type ShutState = Value
  val SHUTSTATE0, SHUTSTATE1, SHUTSTATE2, SHUTSTATE3, SHUTSTATE4, SHUTSTATE5 = Value

  val map = Map[ShutState, String](
    SHUTSTATE0 -> "",
    SHUTSTATE1 -> "",
    SHUTSTATE2 -> "ingame",
    SHUTSTATE3 -> "Player can roll",
    SHUTSTATE4 -> "",
    SHUTSTATE5 -> "Player can shut")

    def message(shutState: ShutState) = {
      map(shutState)
    }

}
