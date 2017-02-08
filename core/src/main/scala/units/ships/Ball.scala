package units.ships

import brols.{Size, V2}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import systems.Creator
import systems.eventhub.events.Event
import systems.eventhub.{EventHub, EventListener}
import systems.physic.{Box2DHelper, Box2DObject, Physic}

/**
  * Created by julien on 08/02/17.
  */
class Ball extends EventListener with Box2DObject {

  def category() = Ball.category
  def bodyType() = Ball.bodyType
  def mask() = Ball.mask
  def size() = Ball.size

  override def createBody(): Body = Box2DHelper.createCircle(this, Ball.size.w, this, Creator.vectorInScreen())
  body.setLinearDamping(0)

  EventHub.registerForCollisions(this)

  def act() = {}

  def draw(batch: ShapeRenderer) = {
    batch.circle(Box2DHelper.screenX(this) + Ball.size.hw, Box2DHelper.screenY(this) + Ball.size.hh, Ball.size.w)
  }

  override def heyListen(event: Event) = event match {
    case _ => println("Ouch ! " + event)
  }
}


object Ball {

  val bodyType = BodyType.KinematicBody
  val size = new Size(10, 10)
  val category = Physic.otherCategory
  val mask = Physic.otherMask

  def get() = {
    val b = new Ball
    b.applyForce(V2.getRnd().scl(600))
    b
  }
}
