package model

import commandAndDirection.Direction

case class Mower(coordinated: Coordinated, direction: Direction.Value) {
  def print(): Unit = {
    println("Coordonnées(x = " + coordinated.x + ", y = " + coordinated.y + ") Orientation " + direction)
  }
}
