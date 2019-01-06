package upem.scala

import commandAndDirection.{Command, Direction}
import model.{Coordinated, Lawn, Mower}
import org.scalatest.{FlatSpec, Matchers}
import upem.scala.HandleMower._

class HandleMowerTest extends FlatSpec with Matchers {

	"move" should "move a mower constructed in NORTH direction" in {
		val lawn: Lawn = new Lawn(new Coordinated(5,5))
		val mower: Mower = new Mower(new Coordinated(1,2), Direction.N)

		move(lawn, mower, Command.D) should equal(new Mower(new Coordinated(1,2), Direction.E))
		move(lawn, mower, Command.G) should equal(new Mower(new Coordinated(1,2), Direction.W))
		move(lawn, mower, Command.A) should equal(new Mower(new Coordinated(1,3), Direction.N))

		//Check if the mower want be out the lawn to North direction
		val mowerOutLawnNorth: Mower = new Mower(new Coordinated(1,5), Direction.N)
		val mowerOutNorth = move(lawn, mowerOutLawnNorth, Command.A)
		mowerOutNorth should equal(new Mower(new Coordinated(1,5), Direction.N))
	}

	"move" should "move a mower constructed in SUD direction" in {
		val lawn: Lawn = new Lawn(new Coordinated(5,5))
		val mower: Mower = new Mower(new Coordinated(1,2), Direction.S)

		move(lawn, mower, Command.D) should equal(new Mower(new Coordinated(1,2), Direction.W))
		move(lawn, mower, Command.G) should equal(new Mower(new Coordinated(1,2), Direction.E))
		move(lawn, mower, Command.A) should equal(new Mower(new Coordinated(1,1), Direction.S))

		//Check if the mower want be out the lawn to Sud direction
		val mowerOutLawnSud: Mower = new Mower(new Coordinated(5,0), Direction.S)
		val mowerOutSud = move(lawn, mowerOutLawnSud, Command.A)
		mowerOutSud should equal(new Mower(new Coordinated(5,0), Direction.S))
	}

	"move" should "move a mower constructed in EAST direction" in {
		val lawn: Lawn = new Lawn(new Coordinated(5,5))
		val mower: Mower = new Mower(new Coordinated(1,2), Direction.E)

		move(lawn, mower, Command.D) should equal(new Mower(new Coordinated(1,2), Direction.S))
		move(lawn, mower, Command.G) should equal(new Mower(new Coordinated(1,2), Direction.N))
		move(lawn, mower, Command.A) should equal(new Mower(new Coordinated(2,2), Direction.E))

		//Check if the mower want be out the lawn to East direction
		val mowerOutLawnEast: Mower = new Mower(new Coordinated(5,1), Direction.E)
		val mowerOutEast = move(lawn, mowerOutLawnEast, Command.A)
		mowerOutEast should equal(new Mower(new Coordinated(5,1), Direction.E))
	}

	"move" should "move a mower constructed in WEST direction" in {
		val lawn: Lawn = new Lawn(new Coordinated(5,5))
		val mower: Mower = new Mower(new Coordinated(1,2), Direction.W)

		move(lawn, mower, Command.D) should equal(new Mower(new Coordinated(1,2), Direction.N))
		move(lawn, mower, Command.G) should equal(new Mower(new Coordinated(1,2), Direction.S))
		move(lawn, mower, Command.A) should equal(new Mower(new Coordinated(0,2), Direction.W))

		//Check if the mower want be out the lawn to West direction
		val mowerOutLawnWest: Mower = new Mower(new Coordinated(0,5), Direction.W)
		val mowerOutWest = move(lawn, mowerOutLawnWest, Command.A)
		mowerOutWest should equal(new Mower(new Coordinated(0,5), Direction.W))
	}
}
