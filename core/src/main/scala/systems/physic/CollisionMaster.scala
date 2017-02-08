package systems.physic

import com.badlogic.gdx.physics.box2d.{Contact, ContactImpulse, ContactListener, Manifold}
import systems.eventhub.EventHub
import units.ships.Ship

/**
  * Created by julien on 24/01/17.
  */
class CollisionMaster extends ContactListener {
  override def preSolve(contact: Contact, oldManifold: Manifold) = {}
  override def postSolve(contact: Contact, impulse: ContactImpulse): Unit = {}
  override def endContact(contact: Contact): Unit = {}

  override def beginContact(c: Contact): Unit = {
    val dataA = c.getFixtureA.getUserData
    val dataB = c.getFixtureB.getUserData

    println(dataA + " : " + dataB)
    if (dataA.isInstanceOf[Ship] && dataB.isInstanceOf[Ship]) {
      EventHub.shipCollision(dataA, dataB)
    }
  }

}