package commandAndDirection

object Command extends  Enumeration {
  type CommandType = Value
  val D = Value("D")
  val G = Value("G")
  val A = Value("A")

  def isPresent(value: String): Boolean = values.exists(_.toString == value)
}
