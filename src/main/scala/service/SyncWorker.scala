package service

import actors.PrinterContainer
import actors.PrinterContainer.SpawnPrinterActor
import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.pattern.ask
import akka.util.Timeout
import model.Printer
import service.SyncWorker.SyncPrinters

import scala.concurrent.duration._

/**
  * Synchronization class responsible for getting entities from external source and creating actors each for them
  */
class SyncWorker(printerContainer: ActorRef) extends Actor with ActorLogging {

  val dummyPrinters = Seq(Printer("1"), Printer("2"), Printer("3"))
  implicit val timeout = Timeout(5 seconds)

  override def receive: Receive = {
    case SyncPrinters => dummyPrinters.foreach(p => printerContainer ? SpawnPrinterActor(p))
  }
}

object SyncWorker {

  case object SyncPrinters

  def props(printerContainer: ActorRef): Props = {
    Props(classOf[SyncWorker], printerContainer)
  }
}
