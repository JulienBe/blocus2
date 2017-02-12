package units.particles

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import draw.Textures

/**
  * Created by julien on 12/02/17.
  */
class Square(var x: Float, var y: Float) {
  def draw(spriteBatch: SpriteBatch) =
    spriteBatch.draw(Textures.square, x, y, 2, 2)
}

object Square {
  def get(x: Float, y: Float) = new Square(x, y)
}
