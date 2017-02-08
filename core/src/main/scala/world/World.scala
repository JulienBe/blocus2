package world

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Array
import main.Rome
import units.ships.Brik

/**
  * Created by julien on 28/01/17.
  */
object World {
  private val briks: Array[Brik] = new Array[Brik]()

  Wall.surround(1f, 1f, Rome.width - 1f, Rome.height - 1f)

  def removeBrik(brik: Brik) = briks.removeValue(brik, true)

  def act() = {
  }

  def render(shapeRenderer: ShapeRenderer) = {
    for (i <- 0 until briks.size)
      briks.get(i).draw(shapeRenderer)
  }
}
