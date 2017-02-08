package units.ships

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import systems.Creator
import systems.eventhub._
import systems.eventhub.events.{Event, JustTouchedEvent, TouchedEvent}
import systems.physic.{Box2DHelper, Physic}

/**
  * Created by julien on 22/01/17.
  */
class PlayerShip extends Ship with EventListener {

  override def category() = Ship.category
  override def bodyType() = Ship.bodyType
  override def size() = Ship.size
  EventHub.registerForInputs(this)

  override protected def createBody: Body =
    Box2DHelper.createCircle(this, Ship.size.w, this, Creator.vectorInScreen())

  override def heyListen(event: Event): Unit = {
    event match {
      case touched: TouchedEvent => Box2DHelper.setPos(this, touched.x - Ship.size.hw, touched.y - Ship.size.hh); return
      case justTouched: JustTouchedEvent => Box2DHelper.setPos(this, justTouched.x - Ship.size.hw, justTouched.y - Ship.size.hh); return
      case _ => println(this + " couldn't handle " + event)
    }
    super.heyListen(event)
  }
}

object PlayerShip {
  val bodyType = BodyType.KinematicBody

  val category = Physic.playerCategory
  val mask = Physic.playerMask
}