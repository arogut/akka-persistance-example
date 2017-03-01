package actors

import akka.actor.{Actor, ActorRef, ActorRefFactory}
import service.SyncWorker

import scala.concurrent.ExecutionContext

trait ActorContainers {
  implicit def ec: ExecutionContext
  implicit def actorRefFactory: ActorRefFactory

  val syncWorker: ActorRef
  val printerSpawner: ActorRef
}

trait DefaultActorContainers extends ActorContainers{
  this: Actor =>

  override val printerSpawner: ActorRef = context.actorOf(PrinterContainer.props())
  override val syncWorker: ActorRef = context.actorOf(SyncWorker.props(printerSpawner))
}
