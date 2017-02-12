package units.briks

import brols.{R, Size}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import systems.Creator
import systems.eventhub.events.Event
import systems.eventhub.{EventHub, EventListener}
import systems.physic.objects.Box2DObject
import systems.physic.{Box2DHelper, Physic}
import units.GameObject

/**
  * Created by julien on 23/01/17.
  */
class Brik extends EventListener with Box2DObject with GameObject {

  def category() = Brik.category
  def bodyType() = Brik.bodyType
  def mask() = Brik.mask
  def size() = Brik.size

  override def createBody(): Body = Box2DHelper.createRectangle(this, Brik.rect, Creator.vectorInScreen())

  EventHub.registerForCollisions(this)

  override def act(delta: Float) = {}

  override def draw(batch: SpriteBatch) = {
//    batch.circle(Box2DHelper.screenX(this) + Brik.size.hw, Box2DHelper.screenY(this) + Brik.size.hh, Brik.size.w)
  }

  override def heyListen(event: Event) = event match {
    case _ => unhandled(event)
  }

}

object Brik {
  def bodyType = BodyType.StaticBody

  val size = new Size(30, 15)
  val rect = R.get().set(0, 0, size.w, size.h)
  val category = Physic.otherCategory
  val mask = Physic.otherMask
}