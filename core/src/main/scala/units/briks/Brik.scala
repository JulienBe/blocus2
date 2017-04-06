package units.briks

import brols.{R, Size}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.{Rectangle, Vector2}
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import main.Rome
import systems.eventhub.events.{BallCollisionEvent, Event}
import systems.eventhub.{EventHub, EventListener}
import systems.physic.objects.Box2DObject
import systems.physic.{Box2DHelper, Physic}
import systems.world.{BonusMaster, Level}
import units.GameObject
import utils.Roll

/**
  * Created by julien on 23/01/17.
  */
class Brik(val anchorX: Float, var anchorY: Float) extends EventListener with Box2DObject with GameObject {

  setPosBox2D(Rome.size.hwB2D + (Rome.size.hwB2D - anchorX), Brik.yStart + Roll.float(Brik.yOffset))
  EventHub.registerForCollisions(this)

  private var hp = 1

  def category(): Short = Brik.category
  def bodyType(): BodyType = Brik.bodyType
  def mask(): Short = Brik.mask
  def size(): Size = Brik.size
  def xp(): Int = Brik.xp

  override def createBody(): Body = Box2DHelper.createRectangle(this, Brik.rect)

  override def centerB2D(): Vector2 = body.getWorldCenter.add(Brik.size.hwB2D, Brik.size.hhB2D)

  override def act(delta: Float): Unit = {
    moveBox2D(
      (anchorX - xB2D()) * delta * Brik.anchorStrength,
      (anchorY - yB2D()) * delta * Brik.anchorStrength)
  }

  override def draw(batch: SpriteBatch): Unit = {}

  override def heyListen(event: Event): Unit = {
    event match {
      case ballEvent: BallCollisionEvent => collidingWithBall(ballEvent)
      case _ => unhandled(event)
    }
  }

  private def collidingWithBall(ballEvent: BallCollisionEvent) = {
    if (ballEvent.objB.equals(this)) {
      hp -= 1
      BonusMaster.roll(this)
      destroy
    }
  }

  def destroy = EventHub.destroy(this)

}

object Brik {
  def bodyType = BodyType.KinematicBody

  val yStart: Float = Rome.size.hB2D * 2
  val yOffset: Float = yStart / 2
  val size = new Size(Rome.size.w / Level.brikPerRow, 20)
  val anchorStrength = 5
  val xp = 10

  val a1: Float = new Vector2(size.hw, size.hh).angle()
  val a2: Float = new Vector2(-size.hw, size.hh).angle()
  val a3: Float = new Vector2(-size.hw, -size.hh).angle()
  val a4: Float = new Vector2(size.hw, -size.hh).angle()

  val rect: Rectangle = R.get().set(0, 0, size.w, size.h)
  val category: Short = Physic.otherCategory
  val mask: Short = Physic.otherMask

  def getScreen(xScreen: Float, yCenter: Float): Brik = {
    getB2B(Box2DHelper.toBoxUnits(xScreen), Box2DHelper.toBoxUnits(yCenter))
  }

  def getB2B(xB2B: Float, yB2B: Float): Brik = {
    new Brik(xB2B, yB2B)
  }
}