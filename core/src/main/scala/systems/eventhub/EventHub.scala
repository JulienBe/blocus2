package systems.eventhub

import com.badlogic.gdx.physics.box2d.Contact
import systems.eventhub.events._
import units.balls.Ball
import units.briks.Brik

import scala.collection.mutable

/**
  * Created by julien on 23/01/17.
  */
object EventHub {

  private val inputsListener: mutable.MutableList[EventListener] = mutable.MutableList()
  private val destroyListener: mutable.MutableList[EventListener] = mutable.MutableList()
  private val collisionsListener: mutable.MutableList[EventListener] = mutable.MutableList()

  def registerForInputs(eventListener: EventListener): mutable.MutableList[EventListener] = addListener(eventListener, inputsListener)
  def registerForDestroy(eventListener: EventListener): mutable.MutableList[EventListener] = addListener(eventListener, destroyListener)
  def registerForCollisions(eventListener: EventListener): mutable.MutableList[EventListener] = addListener(eventListener, collisionsListener)

  private def addListener(eventListener: EventListener, mutableList: mutable.MutableList[EventListener]) = mutableList.+=(eventListener)

  private def tell(obj: Any) = obj match {
    case touched: TouchedEvent => inputsListener.foreach(_.heyListen(touched))
    case brikEvent: DestroyBrik => destroyListener.foreach(_.heyListen(brikEvent))
    case justTouched: JustTouchedEvent => inputsListener.foreach(_.heyListen(justTouched))
    case collisionEvent: CollisionEvent => collisionsListener.foreach(_.heyListen(collisionEvent))
  }

  def mouseMoved(x: Int, y: Int): Unit = tell(new TouchedEvent(x, y))
  def justTouched(x: Int, y: Int): Unit = tell(new JustTouchedEvent(x, y))
  def ballCollision(ball: Ball, objB: Object, contact: Contact): Unit = tell(new BallCollisionEvent(ball, objB, contact))
  def destroy(brik: Brik): Unit = tell(new DestroyBrik(brik))
}