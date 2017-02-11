package systems.physic

import main.Rome
import systems.eventhub.{EventHub, EventListener}
import systems.eventhub.events.{BallCollisionEvent, Event}
import world.Wall

/**
  * Created by julien on 11/02/17.
  */
object CollisionResolver extends EventListener {
  EventHub.registerForCollisions(this)

  override def heyListen(event: Event) = event match {
    case ball: BallCollisionEvent => ballColliding(ball)
    case _ => Rome.logUnhandledEvent(event, this)
  }

  def ballColliding(event: BallCollisionEvent) = {
    val b = event.ball
    event.objB match {
      case wall: Wall => {
        b.dir.x = wall.collisionMultiplier.x * b.dir.x
        b.dir.y = wall.collisionMultiplier.y * b.dir.y
      }
      case _ => Rome.logUnhandledEvent(event, this)
    }
  }
}
