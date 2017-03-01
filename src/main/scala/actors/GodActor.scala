package actors

import akka.actor.{Actor, ActorRefFactory}
import service.SyncWorker.SyncPrinters
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class GodActor extends Actor with DefaultActorContainers {
  override implicit def ec: ExecutionContext = context.dispatcher
  override implicit def actorRefFactory: ActorRefFactory = context

  implicit val timeout = Timeout(5 seconds)

  override def receive: Receive = {
    case _ => syncWorker ? SyncPrinters
  }
}
