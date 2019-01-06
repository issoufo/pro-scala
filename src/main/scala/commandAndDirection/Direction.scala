package commandAndDirection

import commandAndDirection.Command.values

object Direction extends  Enumeration {
  type DirectionType = Value
  val E, W, N, S = Value

  def isPresent(s: String): Boolean = values.exists(_.toString == s)
}
