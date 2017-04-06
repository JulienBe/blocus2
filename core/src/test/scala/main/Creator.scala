package scala.main

import org.scalatest.FlatSpec
import utils.Roll

import scala.util.Random

/**
  * Created by julein on 10/07/16.
  */
class CreatorTest extends FlatSpec {

  behavior of "Creator"

  it should "produce value between bounds" in {
    for (i <- 1 to 100) {
      val min = -100 * i * Random.nextFloat
      val max = 100 * i * Random.nextFloat
      val n = Roll.floatInBounds(min, max)
      assert(n >= min)
      assert(n < max)
    }
  }

}
