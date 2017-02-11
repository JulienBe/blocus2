package units

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.{Rectangle, Vector2}
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.utils.Array
import systems.Creator
import systems.physic.{Box2DHelper, Box2DObject, Physic}

/**
  * Created by julien on 28/01/17.
  */
class Wall(val rectangle: Rectangle, val collisionMultiplier: Vector2) extends Box2DObject with GameObject {
  override def bodyType(): BodyType = Wall.bodyType
  override def category(): Short = Wall.category
  override def mask(): Short = Wall.mask
  override def createBody = Box2DHelper.createRectangle(this, rectangle, this, Creator.vectorInScreen())

  override def act(delta: Float) = {}

  override def draw(batch: SpriteBatch) = {}
}

object Wall {

  val category = Physic.otherCategory
  val mask = Physic.otherMask
  val width = 2
  val bodyType = BodyType.StaticBody
  private val yCollisionInverter = new Vector2(1, -1)
  private val xCollisionInverter = new Vector2(-1, 1)

  def surround(x: Float, y: Float, width: Float, height: Float): Array[Wall] = {
    val bottom =  new Rectangle(x,              y - Wall.width, width,      Wall.width)
    val top =     new Rectangle(x,              height,         width,      Wall.width)
    val left =    new Rectangle(x - Wall.width, y,              Wall.width, height)
    val right =   new Rectangle(width,          y,              Wall.width, height)

    val array = new Array[Wall]()
    array.add(new Wall(bottom, yCollisionInverter))
    array.add(new Wall(top, yCollisionInverter))
    array.add(new Wall(left, xCollisionInverter))
    array.add(new Wall(right, xCollisionInverter))
    array
  }

}
