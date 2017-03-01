package actors

import akka.actor.{Actor, Props}

import scala.concurrent.ExecutionContext

class PrinterSpawner()(implicit executionContext: ExecutionContext) extends Actor{
  override def receive: Receive = {
    case _ => println("lol")
  }
}

object PrinterSpawner {
  def props()(implicit executionContext: ExecutionContext): Props = {
    Props(classOf[PrinterSpawner], executionContext)
  }
}
