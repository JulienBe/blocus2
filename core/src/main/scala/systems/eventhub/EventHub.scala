package systems.eventhub

import com.badlogic.gdx.physics.box2d.Contact
import systems.eventhub.events._
import units.balls.Ball
import units.bonuses.BallBonus
import units.briks.Brik

import scala.collection.mutable

/**
  * Created by julien on 23/01/17.
  */
object EventHub {

  private val gameListener: mutable.MutableList[EventListener] = mutable.MutableList()
  private val inputsListener: mutable.MutableList[EventListener] = mutable.MutableList()
  private val destroyListener: mutable.MutableList[EventListener] = mutable.MutableList()
  private val collisionsListener: mutable.MutableList[EventListener] = mutable.MutableList()

  def registerForGame(eventListener: EventListener): mutable.MutableList[EventListener] = addListener(eventListener, gameListener)
  def registerForInputs(eventListener: EventListener): mutable.MutableList[EventListener] = addListener(eventListener, inputsListener)
  def registerForDestroy(eventListener: EventListener): mutable.MutableList[EventListener] = addListener(eventListener, destroyListener)
  def registerForCollisions(eventListener: EventListener): mutable.MutableList[EventListener] = addListener(eventListener, collisionsListener)

  private def addListener(eventListener: EventListener, mutableList: mutable.MutableList[EventListener]) = mutableList.+=(eventListener)

  private def tell(obj: Any) = obj match {
    case gameLost: GameLost              => gameListener.foreach(_.heyListen(gameLost))
    case addBonus: AddBonusEvent         => gameListener.foreach(_.heyListen(addBonus))
    case bonusCreated: BonusCreatedEvent => gameListener.foreach(_.heyListen(bonusCreated))
//    case addBall: AddBall               => gameListener.foreach(_.heyListen(addBall));
    case touched: TouchedEvent           => inputsListener.foreach(_.heyListen(touched))
    case justTouched: JustTouchedEvent   => inputsListener.foreach(_.heyListen(justTouched))
    case brikEvent: DestroyBrik          => destroyListener.foreach(_.heyListen(brikEvent))
    case ballEvent: DestroyBall          => destroyListener.foreach(_.heyListen(ballEvent))
    case collisionEvent: CollisionEvent  => collisionsListener.foreach(_.heyListen(collisionEvent))
  }

  def mouseMoved(x: Int, y: Int): Unit = tell(new TouchedEvent(x, y))
  def justTouched(x: Int, y: Int): Unit = tell(new JustTouchedEvent(x, y))
  def ballCollision(ball: Ball, objB: Object, contact: Contact): Unit = tell(new BallCollisionEvent(ball, objB, contact))
  def destroy(brik: Brik): Unit = tell(new DestroyBrik(brik))
  def lostBall(b: Ball) = tell(new DestroyBall(b))
  def lost() = tell(new GameLost)
//  def addBall(b: Ball) = tell(new AddBall(b))
  def addBonus(bonus: () => BallBonus) = tell(new AddBonusEvent(bonus))
  def bonusCreated(bonus: BallBonus) = tell(new BonusCreatedEvent(bonus))
}
