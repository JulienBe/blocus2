package systems.eventhub.events

import com.badlogic.gdx.physics.box2d.Contact
import units.balls.Ball

/**
  * Created by julien on 24/01/17.
  */
class CollisionEvent(val contact: Contact) extends Event
class BallCollisionEvent(val ball: Ball, val objB: Object, contact: Contact) extends CollisionEvent(contact)
