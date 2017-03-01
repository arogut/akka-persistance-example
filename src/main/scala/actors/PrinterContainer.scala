package actors

import actors.PrinterContainer.{KillPrinterActor, SpawnPrinterActor, UpdatePrinterActors}
import akka.actor.{Actor, Props}
import model.{Printer, PrinterActor}
import akka.pattern.ask
import akka.util.Timeout
import model.PrinterActor.{Kill, UpdateState}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class PrinterContainer()(implicit executionContext: ExecutionContext) extends Actor{

  implicit val timeout = Timeout(5 seconds)

  val r = new scala.util.Random(31)

  override def receive: Receive = {
    case SpawnPrinterActor(printer) => context.actorOf(PrinterActor.props(printer))
    case KillPrinterActor => context.children.foreach(c => c ? Kill)
    case UpdatePrinterActors => context.children.foreach(c => c ? UpdateState(r.nextInt().toString))
  }
}

object PrinterContainer {

  case class SpawnPrinterActor(printer: Printer)

  case class KillPrinterActor(id: String)

  case object UpdatePrinterActors

  def props()(implicit executionContext: ExecutionContext): Props = {
    Props(classOf[PrinterContainer], executionContext)
  }
}
