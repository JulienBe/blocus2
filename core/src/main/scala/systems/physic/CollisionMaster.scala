package systems.physic

import com.badlogic.gdx.physics.box2d.{Contact, ContactImpulse, ContactListener, Manifold}
import systems.eventhub.EventHub
import units.balls.Ball

/**
  * Created by julien on 24/01/17.
  */
class CollisionMaster extends ContactListener {
  override def preSolve(c: Contact, oldManifold: Manifold): Unit = {}
  override def postSolve(contact: Contact, impulse: ContactImpulse): Unit = {}
  override def endContact(c: Contact): Unit = {}

  override def beginContact(c: Contact): Unit = {
    val dataA = c.getFixtureA.getUserData
    val dataB = c.getFixtureB.getUserData

    ball(dataA, dataB, c)
    ball(dataB, dataA, c)
  }

  private def ball(obj: Object, other: Object, contact: Contact): Unit = obj match {
    case ball: Ball => EventHub.ballCollision(ball, other, contact)
    case _ =>
  }
}