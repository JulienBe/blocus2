package systems.physic

import com.badlogic.gdx.physics.box2d.{Contact, ContactImpulse, ContactListener, Manifold}
import systems.eventhub.EventHub
import units.balls.Ball
import units.briks.Brik

/**
  * Created by julien on 24/01/17.
  */
class CollisionMaster extends ContactListener {
  override def preSolve(contact: Contact, oldManifold: Manifold) = {
    val normal = contact.getWorldManifold.getNormal
    if (normal.angle() % 45 != 0) {
//      normal.rotate(normal.angle() + normal.angle() % 45)
      normal.set(0, 1)
      println("rotated")
    }
  }

  override def postSolve(contact: Contact, impulse: ContactImpulse): Unit = {
    val normal = contact.getWorldManifold.getNormal
    if (normal.angle() % 45 != 0) {
      //      normal.rotate(normal.angle() + normal.angle() % 45)
      normal.set(0, 1)
      println("rotated")
    }
  }

  override def endContact(c: Contact): Unit = {}

  override def beginContact(c: Contact): Unit = {
    val dataA = c.getFixtureA.getUserData
    val dataB = c.getFixtureB.getUserData

    if (dataA.isInstanceOf[Brik] || dataB.isInstanceOf[Brik]) {
      println("============ " + dataA + " ==== " + dataB + " =====")
      println(
        "--   number of c  " +
          c.getWorldManifold.getNumberOfContactPoints +
          "-- friction " +
          c.getFriction +
          "-- points " +
          c.getWorldManifold.getPoints.length +
          " n° of separations : " +
          c.getWorldManifold.getSeparations.length +
          "-- angle normal " +
          c.getWorldManifold.getNormal.angle() +
          " normal :  " +
          c.getWorldManifold.getNormal
      )

    }

    ball(dataA, dataB, c)
    ball(dataB, dataA, c)
  }

  def ball(obj: Object, other: Object, contact: Contact) = obj match {
    case ball: Ball => EventHub.ballCollision(ball, other, contact)
    case _ =>
  }

}