package upem.scala

import java.io.File

import commandAndDirection.Direction
import model.{Coordinated, Lawn, Mower}
import org.scalatest.{FlatSpec, Matchers}
import upem.scala.HandleFile.{mowerWithInstructions, _}

import scala.collection.mutable

class HandleFileTest  extends FlatSpec with Matchers{

	"buildLawn" should "create a lawn from first line of file" in {
		val filePath = "fileTest.txt"
		buildLawn(filePath) should equal (Some(new Lawn(new Coordinated(5, 5))))
	}

	"buildMowerWithInstructions" should "associate instructions to a mower via LinkedHashMap" in {
		val filePath = "fileTest.txt"
		val lawn: Option[Lawn] = buildLawn(filePath)
		val mowerWithInstructions = new mutable.LinkedHashMap[Mower, String]()
		mowerWithInstructions.put(new Mower(new Coordinated(1,2), Direction.withName("N")), "GAGAGAGAA")
		mowerWithInstructions.put(new Mower(new Coordinated(3,3), Direction.withName("E")), "AADAADADDA")

		lawn match {
			case Some(lawn) => buildMowerWithInstructions(lawn) should equal(Some(mowerWithInstructions))
			case _ => println("Mowers coordinates are superior of lawn coordinate")
		}
	}
}
