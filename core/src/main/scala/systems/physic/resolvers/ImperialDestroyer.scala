package systems.physic.resolvers

import systems.eventhub.events.{DestroyBrik, Event}
import systems.eventhub.{EventHub, EventListener}
import systems.physic.Box2DHelper

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