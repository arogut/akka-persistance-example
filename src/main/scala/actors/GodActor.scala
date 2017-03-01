package actors

import akka.actor.{Actor, ActorRefFactory}

import scala.concurrent.ExecutionContext

class GodActor extends Actor with DefaultSpawners {
  override implicit def ec: ExecutionContext = context.dispatcher
  override implicit def actorRefFactory: ActorRefFactory = context

  override def receive: Receive = {
    case _ => println("lol2")
  }
}
