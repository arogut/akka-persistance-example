import actors.GodActor
import actors.GodActor.{GeneratePrinters, KillPrinters, UpdatePrinters}
import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import akka.pattern.ask

import scala.concurrent.duration._

object Boot extends App {
  println("Welcome to app")

  val system = ActorSystem("akka-persistance-example")
  implicit val timeout = Timeout(5 seconds)
  val service = system.actorOf(Props[GodActor], "mapping-service")
  implicit def actorRefFactory = system
  implicit val log = system.log
  implicit val executionContext = system.dispatcher.prepare()

  service ? GeneratePrinters

  //system.scheduler.schedule(5 seconds,5 seconds, service, UpdatePrinters)
  system.scheduler.schedule(60 seconds,1 hour, service, KillPrinters)
}
