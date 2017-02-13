package systems.physic

import brols.V2
import com.badlogic.gdx.math.Vector2
import main.Rome
import systems.eventhub.{EventHub, EventListener}
import systems.eventhub.events.{BallCollisionEvent, Event}
import units.Wall
import units.balls.Ball
import units.briks.Brik

/**
  * Created by julien on 11/02/17.
  */
object CollisionResolver extends EventListener {
  EventHub.registerForCollisions(this)

  override def heyListen(event: Event): Unit = event match {
    case ball: BallCollisionEvent => ballColliding(ball)
    case _ => Rome.logUnhandledEvent(event, this)
  }

  def ballColliding(event: BallCollisionEvent): Unit = {
    val b = event.ball
    event.objB match {
      case wall: Wall => collidingWall(b, wall)
      case brik: Brik => collidingBrik(b, brik)
      case _ => Rome.logUnhandledEvent(event, this)
    }
  }

  private def collidingBrik(b: Ball, brik: Brik) = {
    val angle = V2.getTmp().set(b.centerB2D().x - brik.centerB2D().x, b.centerB2D().y - brik.centerB2D().y).angle()
    val dir = b.body.getLinearVelocity
    if (angle > Brik.a2 && angle <= Brik.a3)
      collidingOnTheLeftSide(dir)
    if (angle > Brik.a3 && angle <= Brik.a4)
      collidingOnTheBottomSide(dir)
    if (angle > Brik.a4 || angle <= Brik.a1)
      collidingOnTheRightSide(dir)
    if (angle > Brik.a1 && angle <= Brik.a2)
      collidingOnTheTopSide(dir)
    b.setDir(dir)
  }

  private def collidingWall(b: Ball, wall: Wall) = {
    val dir = b.body.getLinearVelocity
    wall.tag match {
      case Wall.top => collidingOnTheBottomSide(dir)
      case Wall.left => collidingOnTheRightSide(dir)
      case Wall.right => collidingOnTheLeftSide(dir)
      case Wall.bottom => collidingOnTheTopSide(dir)
    }
    b.setDir(dir)
  }

  private def collidingOnTheBottomSide(dir: Vector2) = dir.y = -Math.abs(dir.y)
  private def collidingOnTheRightSide(dir: Vector2) = dir.x = Math.abs(dir.x)
  private def collidingOnTheLeftSide(dir: Vector2) = dir.x = -Math.abs(dir.x)
  private def collidingOnTheTopSide(dir: Vector2) = dir.y = Math.abs(dir.y)
}
