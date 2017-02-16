package systems.physic

import systems.eventhub.{EventHub, EventListener}
import systems.eventhub.events.{DestroyBrik, Event}

/**
  * Created by julien on 15/02/17.
  */
object ImperialDestroyer extends EventListener {
  EventHub.registerForDestroy(this)

  override def heyListen(event: Event): Unit = event match {
    case brik: DestroyBrik => Box2DHelper.removeBody(brik.brik)
    case _ => unhandled(event)
  }
}