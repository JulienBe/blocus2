package systems.eventhub

import systems.eventhub.events.Event

/**
  * Created by julien on 23/01/17.
  */
trait EventListener {
  def heyListen(event: Event)
}
