package model

import akka.actor.Props
import akka.persistence.PersistentActor

class PrinterActor(printer: Printer) extends PersistentActor {

  println(persistenceId + " created")

  override def receiveRecover: Receive = {
    case _ => println("te")
  }

  override def receiveCommand: Receive = {
    case _ => println("tete")
  }

  override def persistenceId: String = printer.id
}

object PrinterActor {

  def props(printer: Printer): Props = {
    Props(classOf[PrinterActor], printer)
  }
}
