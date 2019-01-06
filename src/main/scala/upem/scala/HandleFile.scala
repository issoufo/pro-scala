package upem.scala

import java.io.File

import commandAndDirection.{Command, Direction}
import model.{Coordinated, Lawn, Mower}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.{Failure, Success, Try}

object HandleFile {

	private val isNumber = (value: String) => if (Try(value.toInt).isSuccess) true else false
	private val stringToPositiveInt = (value: String) => if(isNumber(value))value.toInt else -1
	private lazy val readFile = (filePath: String) => Try(Source.fromFile(filePath).getLines.toList)
	private val fileLines = new ListBuffer[String]
	private val mowerWithInstructions = new mutable.LinkedHashMap[Mower, String]

	def buildLawn(filePath: String): Option[Lawn] = {
		readFile(filePath) match {
			case Success(linesFromFile) => lawnBuilder(linesFromFile)
			case Failure(f) => None
		}
	}

	def buildMowerWithInstructions(lawn: Lawn): Option[mutable.LinkedHashMap[Mower, String]] = {
		val mowersInfos = fileLines.filter(line => fileLines.indexOf(line)%2 == 0)
		val mowersInstructions = fileLines.filter(line => fileLines.indexOf(line)%2 != 0)
		if(mowersInfos.size != mowersInstructions.size){
			None
		}else {
			computeValidMowers(mowersInfos, mowersInstructions, lawn)
			mowerWithInstructions.size match {
				case 0 => None
				case _ => {
					if(mowerWithInstructions.size != mowersInfos.size){
						println("\n" + (mowersInfos.size - mowerWithInstructions.size) + " tondeuses on été ignorées car leurs données ne sont pas dans le bon format.")
					}
					Some(mowerWithInstructions)
				}
			}
		}
	}

	private def isValidInstructionsOrDirection(mowerInstructions: String): Boolean ={
		val checkDirection = mowerInstructions.filter(c => Direction.isPresent(""+c) || Command.isPresent(""+c))
		if(checkDirection.length == mowerInstructions.length)
			true
		else
			false
	}

	private def lawnBuilder(linesFromFile: List[String]): Option[Lawn] = {
		val lines = linesFromFile.filter(!_.isEmpty)
		if ((lines.size-1) % 2 != 0){
			None
		}else{
			lines.size match {
				case (0 | 1 | 2) => None
				case _ => computeLawn(lines)
			}
		}
	}

	private def computeLawn(lines: List[String]): Option[Lawn] = {
		val lawnCoordonnate = lines.head
		val coordonnate = lawnCoordonnate.split(" ")
		coordonnate.length match {
			case x if(x != 2) => None
			case _ =>{
				val x = coordonnate(0)
				val y = coordonnate(1)
				if(isNumber(x) && isNumber(y)){
					if(x.toInt >= 0 && y.toInt >= 0) {
						lines.tail.foreach(x => fileLines += x)
						Some(new Lawn(new Coordinated(x.toInt, y.toInt)))
					}else
						None
				}else
					None
			}
		}
	}

	private def computeValidMowers(mowersInfos: ListBuffer[String], mowersInstructions: ListBuffer[String], lawn: Lawn) = {
		val listMowers = mowersInfos.filter(line => {
			val mowerInfos = line.split(" ")
			val mowerInstructions = mowersInstructions.apply(mowersInfos.indexOf(line))
			mowerInfos.length == 3 && isNumber(mowerInfos(0)) && isNumber(mowerInfos(1)) && !isNumber(mowerInfos(2)) &&
			  isValidInstructionsOrDirection(mowerInfos(2)) && isValidInstructionsOrDirection(mowerInstructions) &&
			  stringToPositiveInt(mowerInfos(0)) >= 0 && stringToPositiveInt(mowerInfos(1)) >= 0 &&
			  stringToPositiveInt(mowerInfos(0)) <= lawn.coordinated.x && stringToPositiveInt(mowerInfos(1)) <= lawn.coordinated.y
		}).map(line =>{
			val mowerInfos = line.split(" ")
			val instructions = mowersInstructions.apply(mowersInfos.indexOf(line))
			mowerWithInstructions.put(new Mower(new Coordinated(stringToPositiveInt(mowerInfos(0)), stringToPositiveInt(mowerInfos(1))), Direction.withName(mowerInfos.apply(2))), instructions)
		})
	}
}