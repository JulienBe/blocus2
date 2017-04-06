package systems.eventhub.events

import com.badlogic.gdx.physics.box2d.Contact
import systems.physic.objects.Box2DObject
import units.balls.Ball
import units.bonuses.{BallBonus, Bonus}
import units.briks.Brik

/**
  * Created by julien on 23/01/17.
  */
trait Event

class GameLost extends Event

class DestroyBall(val ball: Ball) extends Event
class DestroyBrik(val brik: Brik) extends Event

class CollisionEvent(val contact: Contact) extends Event
class BallCollisionEvent(val ball: Ball, val objB: Object, contact: Contact) extends CollisionEvent(contact)

class TouchEvent(val x: Int, val y: Int) extends Event
class TouchedEvent(x: Int, y: Int) extends TouchEvent(x, y)
class JustTouchedEvent(x: Int, y: Int) extends TouchEvent(x, y)

class AddEvent(val add: () => Box2DObject) extends Event
class AddBonusEvent(val bonus: () => BallBonus) extends AddEvent(bonus)
//class AddBall(val ball: Ball) extends Event
class BonusCreatedEvent(val bonus: BallBonus) extends Event
