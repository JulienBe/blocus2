package units

import brols.Size
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import systems.Creator
import systems.physic.objects.KinematicObject
import systems.physic.{Box2DHelper, Physic}

/**
  * Created by julien on 11/02/17.
  */
class Paddle extends GameObject with KinematicObject {

  override def createBody(): Body = Box2DHelper.createRectangle(this, Paddle.rectangle, Creator.vectorInScreen())
  override def bodyType(): BodyType = Paddle.bodyType
  override def category(): Short = Paddle.category
  override def mask(): Short = Paddle.mask

  override def act(delta: Float): Unit = {}
  override def draw(batch: SpriteBatch): Unit = {}

}

object Paddle {
  val category = Physic.playerCategory
  val mask = Physic.playerMask
  val size = new Size(30, 6)
  val bodyType = BodyType.KinematicBody
  val rectangle = new Rectangle(0, 0, size.w, size.h)
}