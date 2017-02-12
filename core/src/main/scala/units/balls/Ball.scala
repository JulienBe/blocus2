package units.balls

import brols.{Size, V2}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import draw.Textures
import systems.Creator
import systems.physic.objects.KinematicObject
import systems.physic.{Box2DHelper, Physic}
import units.GameObject

/**
  * Created by julien on 08/02/17.
  */
class Ball extends KinematicObject with GameObject {

  var time = 0f
  def category() = Ball.category
  def bodyType() = Ball.bodyType
  def mask() = Ball.mask
  def size() = Ball.size

  override def createBody(): Body = Box2DHelper.createCircle(this, Ball.size.w, Creator.vectorInScreen())

  override def act(delta: Float) = {
    time += delta
//    updatePhysic(delta)
    if (body.getLinearVelocity.angle() % 45 != 0)
      body.setLinearVelocity(1, 0)
  }

  override def draw(batch: SpriteBatch) = {
    batch.draw(Textures.ball, Box2DHelper.centerScreenX(this) - Ball.size.qw, Box2DHelper.centerScreenY(this) - Ball.size.qh, Ball.size.w, Ball.size.h)
  }

}

object Ball {

  val bodyType = BodyType.DynamicBody
  val size = new Size(16, 16)
  val category = Physic.otherCategory
  val mask = Physic.otherMask

  def get() = {
    val b = new Ball
    b.applyForce(V2.getRnd().scl(0.1f))
    b
  }
}
