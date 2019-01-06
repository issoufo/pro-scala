package commandAndDirection

import org.scalatest.{FlatSpec, Matchers}
import commandAndDirection.Direction.isPresent

class DirectionTest extends FlatSpec with Matchers {

	"isPresent" should "check if direction is valid" in {
		isPresent("E") should be(true)
		isPresent("W") should be(true)
		isPresent("N") should be(true)
		isPresent("S") should be(true)
		isPresent("e") should be(false)
		isPresent("w") should be(false)
		isPresent("o") should be(false)
	}
}
