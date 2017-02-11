package units

import com.badlogic.gdx.graphics.glutils.ShapeRenderer

/**
  * Created by julien on 08/02/17.
  */
trait GameObject {

  def act(delta: Float)
  def draw(batch: ShapeRenderer)

}
