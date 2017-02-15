package systems.brains

import com.badlogic.gdx.math.Vector2
import utils.Creator

/**
  * Created by julien on 29/01/17.
  */
abstract class Brain(val size: Int) {
  val weigths = Array.fill(size)(new Vector2(Creator.vectorInScreen()))

  var fitness = 0
  def sum(others: Array[Vector2], you: Array[Vector2])
}
