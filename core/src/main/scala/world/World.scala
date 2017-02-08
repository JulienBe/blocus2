package world

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Array
import main.Rome
import units.ships.{Enemy, PlayerShip}

/**
  * Created by julien on 28/01/17.
  */
object World {
  var nextImpulse = 0f

  var playerShips: Array[PlayerShip] = new Array[PlayerShip]()
  var enemyShips: Array[Enemy] = new Array[Enemy]()

  Wall.surround(1f, 1f, Rome.width - 1f, Rome.height - 1f)

  def removeEnemy(enemy: Enemy) = enemyShips.removeValue(enemy, true)

  def act() = {
    //    if (playerShips.size == 0)
    //      playerShips.+=(new PlayerShip)
    if (enemyShips.size <= 15)
      enemyShips.add(Enemy.get())

    for (i <- 0 until enemyShips.size)
      enemyShips.get(i).act()
  }

  def render(shapeRenderer: ShapeRenderer) = {
    for (i <- 0 until enemyShips.size)
      enemyShips.get(i).draw(shapeRenderer)
  }
}
