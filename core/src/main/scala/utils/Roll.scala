package utils

import brols.V2
import com.badlogic.gdx.math.Vector2
import main.Rome

import scala.util.Random

/**
  * Created by julien on 10/07/16.
  */
object Roll {

  def vectorInScreen(): Vector2 = V2.getTmp().set(floatInBounds(0, Rome.size.w), floatInBounds(0, Rome.size.h))

  def float: Float = Random.nextFloat

  def float(yOffset: Float): Float = float * yOffset

  def degree: Float = float * 360

  def boolean: Boolean = Random.nextBoolean

  def int(max: Int): Int = Random.nextInt(max)

  def floatInBounds(min: Float, max: Float): Float = min + (float * (max - min))

  def intInBounds(min: Int, max: Int): Int = min + Random.nextInt(max)

  def positiveValueInBounds(min: Float, max: Float): Float = {
    if (min < 0) floatInBounds(0, max)
    else floatInBounds(min, max)
  }

  def valueInBoundsWithCheck(min: Float, max: Float, checkMin: Float, checkMax: Float): Float = {
    floatInBounds(if (min < checkMin) checkMin else min, if (max > checkMax) checkMax else max)
  }

  /**
    * >= min && < max
    */
  def testBound(min: Float, max: Float, value: Float): Boolean = value >= min && value < max

  def randomIntNot(ceiling: Int, not: Int): Int = {
    var rep = Random.nextInt(ceiling)
    if (rep == not)
      rep = (rep + 1) % ceiling
    rep
  }

}
