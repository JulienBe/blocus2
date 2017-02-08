package units.ships

import brols.Size
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import systems.Creator
import systems.eventhub.events.Event
import systems.eventhub.{EventHub, EventListener}
import systems.physic.{Box2DHelper, Box2DObject, Physic}

/**
  * Created by julien on 23/01/17.
  */
abstract class Ship extends EventListener with Box2DObject {

  def category() = Ship.category
  def bodyType() = Ship.bodyType
  def mask() = Ship.mask
  def size() = Ship.size
  val body = createBody

  protected def createBody = Box2DHelper.createCircle(this, Ship.size.w, this, Creator.vectorInScreen())

  EventHub.registerForCollisions(this)


  def act() = {}
  def draw(batch: ShapeRenderer) = {
    batch.circle(Box2DHelper.screenX(this) + Ship.size.hw, Box2DHelper.screenY(this) + Ship.size.hh, Ship.size.w)
  }

  override def heyListen(event: Event) = event match {
    case _ => println("Ouch ! " + event)
  }

}

object Ship {
  def bodyType = BodyType.DynamicBody

  val size = new Size(10, 10)
  val category = Physic.otherCategory
  val mask = Physic.otherMask
}