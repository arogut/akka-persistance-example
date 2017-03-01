package actors

import akka.actor.{Actor, ActorRef, ActorRefFactory}

import scala.concurrent.ExecutionContext

trait Spawners {
  implicit def ec: ExecutionContext
  implicit def actorRefFactory: ActorRefFactory

  val printerSpawner: ActorRef
}

trait DefaultSpawners extends Spawners{
  this: Actor =>

  val printerSpawner: ActorRef = context.actorOf(PrinterSpawner.props())
}
