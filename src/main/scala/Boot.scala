import actors.GodActor
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

  service ? ""
}
