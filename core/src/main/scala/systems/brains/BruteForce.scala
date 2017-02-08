package systems.brains

import com.badlogic.gdx.math.Vector2

/**
  * Created by julien on 29/01/17.
  */
class BruteForce(size: Int) extends Brain(size){
  def sum(inputs: Array[Vector2]) = {
    val sum = new Vector2()
    for (i <- 0 until weigths.length)
      sum.set(inputs(i).x + weigths(i).x, inputs(i).y + weigths(i).y)
    sum
  }

  override def sum(others: Array[Vector2], you: Array[Vector2]): Unit = {}
}
