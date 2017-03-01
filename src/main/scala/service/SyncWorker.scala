package service

import akka.actor.{Actor, ActorLogging, Props}
import service.SyncWorker.SyncPrinters

import scala.concurrent.ExecutionContext

/**
  * Synchronization class responsible for getting entities from external source and creating actors each for them
  */
class SyncWorker extends Actor with ActorLogging{
  override def receive: Receive = {
    case SyncPrinters => println("get printers and spawn actors for each them")
  }
}

object SyncWorker {

  case object SyncPrinters

  def props()(implicit executionContext: ExecutionContext): Props = {
    Props(classOf[SyncWorker], executionContext)
  }
}
