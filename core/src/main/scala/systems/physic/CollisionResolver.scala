package systems.physic

import brols.V2
import com.badlogic.gdx.math.Vector2
import main.Rome
import systems.eventhub.{EventHub, EventListener}
import systems.eventhub.events.{BallCollisionEvent, Event}
import units.{Paddle, Wall}
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
      case paddle: Paddle => collidingPaddle(b, paddle)
      case _ => Rome.logUnhandledEvent(event, this)
    }
  }

  private def collidingPaddle(b: Ball, paddle: Paddle) = {
    val angle = getAngle(b, paddle.centerB2D())
    val dir = b.getDir()
    val dirAngle = dir.angle()
//      dir.rotate(180 - (Paddle.p6ToP1Angle - dirAngle))
    // far right
    if (angle > Paddle.angle1 && angle <= Paddle.angle2)
      dir.setAngle(Paddle.angle2)
    if (angle > Paddle.angle2 && angle <= Paddle.angle3)
      dir.setAngle(Paddle.angle3)
    if (angle > Paddle.angle3 && angle <= Paddle.angle4)
      collidingOnTheTopSide(dir)
    if (angle > Paddle.angle4 && angle <= Paddle.angle5)
      dir.setAngle(Paddle.angle4)
    if (angle > Paddle.angle5 && angle <= Paddle.angle6)
      dir.setAngle(Paddle.angle5)
    b.setDir(dir)
  }

  private def collidingBrik(b: Ball, brik: Brik) = {
    val angle = getAngle(b, brik.centerB2D())
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

  private def getAngle(b: Ball, center: Vector2) = {
    V2.getTmp().set(b.centerB2D().x - center.x, b.centerB2D().y - center.y).angle()
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
