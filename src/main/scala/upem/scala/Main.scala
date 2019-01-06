package upem.scala

import java.io.File
import java.util.logging.Logger

import commandAndDirection.Command
import model.Mower

import scala.collection.mutable

object Main extends App {

	private val filePath = "fileTest.txt"
	private val logger = Logger.getLogger(Main.toString)

	HandleFile.buildLawn(filePath) match {
		case None => {
			logger.severe("Either the file format is bad, in which case make sure that the lawn and all mowers data are in the correct format \n " +
			  				"	   either one of the coordinates of the lawn is negative.")
			None
		}
		case Some(lawn) => {
			HandleFile.buildMowerWithInstructions(lawn) match {
				case None => {
					logger.severe("Either the coordinates of the mowers are higher than those of the lawn or they are negative. Make sure that x and y are positive " +
					  				"\n   	   and less than or equal to that of the lawn and that there are as many mowers as there are instructions.")
					None
				}
				case Some(mowerAndInstructions) => {
					println("***************** Departure Mower(s) *****************")
					mowerAndInstructions.foreach(x => println(x._1.coordinated.x + " " + x._1.coordinated.y + " " + x._1.direction))

					println("***************** Arrival Mower(s) *****************")
					mowerAndInstructions.foreach { case (mower, instructions) => {
						val mowerChanged = new mutable.HashMap[Mower, Char]()
						instructions.foreach(command =>{
							if(mowerChanged.isEmpty){
								val m = HandleMower.move(lawn, mower, Command.withName(""+command))
								mowerChanged.put(m,command)
							}else{
								val mow = mowerChanged.keys.toList.apply(0)
								mowerChanged.remove(mow)
								val m = HandleMower.move(lawn, mow, Command.withName(""+command))
								mowerChanged.put(m, command)
							}
						})
						if (!mowerChanged.isEmpty){
							mowerChanged.keys.toList.apply(0).print()
						}else
							mower.print()
					}}
					//println("****************************************************")
				}
			}
		}
	}
}
