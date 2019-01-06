package commandAndDirection

import org.scalatest.{FlatSpec, Matchers}
import commandAndDirection.Command.isPresent

class CommandTest extends FlatSpec with Matchers  {

	"isPresent" should "check if command is valid" in {
		isPresent("A") should be(true)
		isPresent("D") should be(true)
		isPresent("G") should be(true)
		isPresent("T") should be(false)
		isPresent("T") should be(false)
	}
}
