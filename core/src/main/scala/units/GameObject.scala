package units

import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
  * Created by julien on 08/02/17.
  */
trait GameObject {

  def act(delta: Float)
  def draw(batch: SpriteBatch)

}
