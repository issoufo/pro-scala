package upem.scala

import commandAndDirection.{Command, Direction}
import model.{Coordinated, Lawn, Mower}

object HandleMower {

  def move(lawn: Lawn, mower: Mower, command: Command.Value): Mower = command match {
    case Command.D => mower.copy(direction = changeDirection(mower.direction)._1)
    case Command.G => mower.copy(direction = changeDirection(mower.direction)._2)
    case Command.A => mower.copy(coordinated = changeCoordinated(lawn, mower.coordinated, mower.direction), direction = mower.direction)
    case _ => mower
  }

  private def changeDirection(currentDirection: Direction.Value): (Direction.Value, Direction.Value) = currentDirection match  {
    case Direction.N => (Direction.E, Direction.W)
    case Direction.S => (Direction.W, Direction.E)
    case Direction.E => (Direction.S, Direction.N)
    case Direction.W => (Direction.N, Direction.S)
    case  _ => (currentDirection, currentDirection)
  }

  private def changeCoordinated(lawn: Lawn, coordinated: Coordinated, direction: Direction.Value): Coordinated = direction match {
    case Direction.N => {
      val newY = coordinated.y + 1
      if (newY <= lawn.coordinated.y){
        coordinated.copy(y = newY)
      }else
        coordinated
    }

    case Direction.S => {
      val newY = coordinated.y - 1
      if (newY >= 0){
        coordinated.copy(y = newY)
      }else
        coordinated
    }

    case Direction.E => {
      val newX = coordinated.x + 1
      if (newX <= lawn.coordinated.x){
        coordinated.copy(x = newX)
      }else
        coordinated
    }

    case Direction.W => {
      val newX = coordinated.x - 1
      if (newX >= 0){
        coordinated.copy(x = newX)
      }else
        coordinated
    }

    case _ => coordinated
  }
}
