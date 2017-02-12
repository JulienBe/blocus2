package units.briks

import brols.{R, Size}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
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

  override def createBody(): Body = Box2DHelper.createRectangle(this, Brik.rect)


  EventHub.registerForCollisions(this)

  override def center(): Vector2 = body.getWorldCenter.add(Brik.size.boxhw, Brik.size.boxhh)

  override def act(delta: Float) = {}

  override def draw(batch: SpriteBatch) = {
//    batch.circle(Box2DHelper.screenX(this) + Brik.size.hw, Box2DHelper.screenY(this) + Brik.size.hh, Brik.size.w)
  }

  override def heyListen(event: Event) = event match {
    case _ => unhandled(event)
  }
}

object Brik {
  def bodyType = BodyType.KinematicBody

  val size = new Size(60, 20)
  val a1 = new Vector2(size.hw, size.hh).angle()
  val a2 = new Vector2(-size.hw, size.hh).angle()
  val a3 = new Vector2(-size.hw, -size.hh).angle()
  val a4 = new Vector2(size.hw, -size.hh).angle()

  val rect = R.get().set(0, 0, size.w, size.h)
  val category = Physic.otherCategory
  val mask = Physic.otherMask
}