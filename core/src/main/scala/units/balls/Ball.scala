package units.balls

import brols.{Size, V2}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import systems.Creator
import systems.physic.{Box2DHelper, KinematicObject, Physic}
import units.GameObject

/**
  * Created by julien on 08/02/17.
  */
class Ball extends KinematicObject with GameObject {

  def category() = Ball.category
  def bodyType() = Ball.bodyType
  def mask() = Ball.mask
  def size() = Ball.size

  override def createBody(): Body = Box2DHelper.createCircle(this, Ball.size.w, Creator.vectorInScreen())

  override def act(delta: Float) = updatePhysic(delta)

  override def draw(batch: ShapeRenderer) = {
    batch.circle(Box2DHelper.screenX(this) + Ball.size.hw, Box2DHelper.screenY(this) + Ball.size.hh, Ball.size.w)
  }

}

object Ball {

  val bodyType = BodyType.DynamicBody
  val size = new Size(10, 10)
  val category = Physic.otherCategory
  val mask = Physic.otherMask

  def get() = {
    val b = new Ball
    b.applyForce(V2.getRnd().scl(6))
    b
  }
}
