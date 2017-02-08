package units.ships

import brols.Size
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
  def category() = Brik.category
  def bodyType() = Brik.bodyType
  def mask() = Brik.mask
  def size() = Brik.size

  override def createBody(): Body = Box2DHelper.createCircle(this, Brik.size.w, this, Creator.vectorInScreen())

  EventHub.registerForCollisions(this)

  def act() = {}

  def draw(batch: ShapeRenderer) = {
    batch.circle(Box2DHelper.screenX(this) + Brik.size.hw, Box2DHelper.screenY(this) + Brik.size.hh, Brik.size.w)
  }

  override def heyListen(event: Event) = event match {
    case _ => println("Ouch ! " + event)
  }
}


object Ball {
  def bodyType = BodyType.DynamicBody

  val size = new Size(10, 10)
  val category = Physic.otherCategory
  val mask = Physic.otherMask
}