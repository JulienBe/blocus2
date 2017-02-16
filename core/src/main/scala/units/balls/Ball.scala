package units.balls

import brols.{Size, V2}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import draw.Textures
import systems.physic.objects.Box2DObject
import systems.physic.{Box2DHelper, Physic}
import units.{GameObject, Paddle}

/**
  * Created by julien on 08/02/17.
  */
class Ball extends Box2DObject with GameObject {

  var time = 0f
  def category(): Short = Ball.category
  def bodyType(): BodyType = Ball.bodyType
  def mask(): Short = Ball.mask
  def size(): Size = Ball.size

  override def setDir(x: Float, y: Float): Unit = {
    val originalSpeed = getDir().len()
    super.setDir(x, y)
    getDir().nor().scl(originalSpeed)
  }

  override def createBody(): Body = Box2DHelper.createCircle(this, Ball.size.w)

  override def act(delta: Float): Unit = {
    time += delta
  }

  override def draw(batch: SpriteBatch): Unit = {
    batch.draw(Textures.ball, Box2DHelper.centerScreenX(this) - Ball.size.qw, Box2DHelper.centerScreenY(this) - Ball.size.qh, Ball.size.w, Ball.size.h)
  }
}

object Ball {

  val bodyType = BodyType.DynamicBody
  val size = new Size(16, 16)
  val defaultXB2B: Float = Paddle.defaultXB2D + 1
  val defaultYB2B: Float = Paddle.y + 1
  val defaultDir: Vector2 = V2.get(-1, -1)
  val category: Short = Physic.otherCategory
  val mask: Short = Physic.otherMask

  def get(): Ball = new Ball
}