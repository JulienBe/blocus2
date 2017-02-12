package units

import brols.Size
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.{Rectangle, Vector2}
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.utils.Array
import systems.Creator
import systems.physic.objects.Edge
import systems.physic.{Box2DHelper, Physic}

/**
  * Created by julien on 28/01/17.
  */
class Wall(val rectangle: Rectangle, collisionMultiplier: Vector2) extends Edge(collisionMultiplier) with GameObject {
  override def bodyType(): BodyType = Wall.bodyType
  override def category(): Short = Wall.category
  override def mask(): Short = Wall.mask
  override def createBody = Box2DHelper.createRectangle(this, rectangle, Creator.vectorInScreen())
  override def act(delta: Float) = {}
  override def draw(batch: SpriteBatch) = {}
}

object Wall {

  val category = Physic.otherCategory
  val mask = Physic.otherMask
  val size = new Size(2, 2)
  val bodyType = BodyType.StaticBody
  private val yCollisionInverter = new Vector2(1, -1)
  private val xCollisionInverter = new Vector2(-1, 1)

  def surround(x: Float, y: Float, width: Float, height: Float): Array[Wall] = {
    val rectangles = Edge.surround(x, y, width, height, size)
    val array = new Array[Wall]()
    array.add(new Wall(rectangles(Edge.bottom), yCollisionInverter))
    array.add(new Wall(rectangles(Edge.top), yCollisionInverter))
    array.add(new Wall(rectangles(Edge.left), xCollisionInverter))
    array.add(new Wall(rectangles(Edge.right), xCollisionInverter))
    array
  }

}
