package model

import akka.actor.Props
import akka.persistence.{PersistentActor, RecoveryCompleted, SnapshotOffer}
import model.PrinterActor.{Kill, PrintState, Snapshot, UpdateState}

class PrinterActor(var printer: Printer) extends PersistentActor {

  self ! PrintState

  override def receiveRecover: Receive = {
    case RecoveryCompleted => println("recovery completed")
    case printer: String => updatePrinter(printer)
    case SnapshotOffer(_, snapshot: Printer) => printer = snapshot
  }

  override def receiveCommand: Receive = {
    case UpdateState(p) => persist(p)(updatePrinter)
    case PrintState => println(printer.displayName)
    case Snapshot => saveSnapshot(printer)
    case Kill => println("Killing: " + persistenceId); context.stop(self)
  }

  override def persistenceId: String = printer.id

  def updatePrinter(displayName: String): Unit = {
    printer = printer.copy(displayName = Some(displayName))
    self ! PrintState
  }
}

object PrinterActor {

  case object Snapshot

  case object PrintState

  case class UpdateState(displayName: String)

  case object Kill


  def props(printer: Printer): Props = {
    Props(classOf[PrinterActor], printer)
  }
}
