package systems.eventhub

import main.Rome
import systems.eventhub.events.Event

/**
  * Created by julien on 23/01/17.
  */
trait EventListener {
  def heyListen(event: Event)
  protected def unhandled(event: Event): Unit = Rome.logUnhandledEvent(event, this)
}