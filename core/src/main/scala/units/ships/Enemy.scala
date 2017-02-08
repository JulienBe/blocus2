package units.ships

import com.badlogic.gdx.math.Vector2
import systems.Creator
import systems.brains.BruteForce
import systems.eventhub.events.{Event, ShipCollisionEvent}
import systems.physic.{Box2DHelper, Physic}
import world.World

/**
  * Created by julien on 23/01/17.
  */
class Enemy extends Ship {

  val dir = new Vector2(Vector2.X).rotate(Creator.degree).scl(80)
  val brain = new BruteForce(400)
  Box2DHelper.addAntennas(this)

  override def heyListen(event: Event): Unit = {
    event match {
      case shipCollision: ShipCollisionEvent =>
        Physic.bodyToClean(body)
        World.removeEnemy(this)
        return
    }
    super.heyListen(event)
  }

  override def act(): Unit = {

  }
}

object Enemy {

  val impulseFreq = 1f

  def get() = {
    new Enemy
  }
}