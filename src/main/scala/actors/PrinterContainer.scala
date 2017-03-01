package actors

import actors.PrinterContainer.{KillPrinterActor, SpawnPrinterActor}
import akka.actor.{Actor, Props}
import model.{Printer, PrinterActor}

import scala.concurrent.ExecutionContext

class PrinterContainer()(implicit executionContext: ExecutionContext) extends Actor{
  override def receive: Receive = {
    case SpawnPrinterActor(printer) => context.actorOf(PrinterActor.props(printer))
    case KillPrinterActor => print("kill actor")
  }
}

object PrinterContainer {

  case class SpawnPrinterActor(printer: Printer)

  case class KillPrinterActor(id: String)

  def props()(implicit executionContext: ExecutionContext): Props = {
    Props(classOf[PrinterContainer], executionContext)
  }
}
