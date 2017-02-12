package systems.physic.objects

import brols.{R, Size}
import com.badlogic.gdx.math.{Rectangle, Vector2}

/**
  * Created by julien on 11/02/17.
  */
abstract class Edge(val collisionMultiplier: Vector2) extends Box2DObject {
}

object Edge {
  val bottom = 0
  val top = 1
  val right = 2
  val left = 3

  def surround(x: Float, y: Float, width: Float, height: Float, size: Size): Array[Rectangle] = {
    val array = Array(R.get(), R.get(), R.get(), R.get())
    val bottomR = new Rectangle(x, y - size.w, width, size.w)
    val topR = new Rectangle(x, height, width, size.w)
    val leftR = new Rectangle(x - size.w, y, size.w, height)
    val rightR = new Rectangle(width, y, size.w, height)
    array(bottom) = bottomR
    array(top) = topR
    array(left) = leftR
    array(right) = rightR
    array
  }
}
