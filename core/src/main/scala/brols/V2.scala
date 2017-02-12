package brols

import com.badlogic.gdx.math.Vector2
import systems.Creator

/**
  * Created by julien on 08/02/17.
  */
object V2 {
  private val tmp = new Vector2()
  def getTmp() = tmp
  def get() = new Vector2()
  def getRnd() = get().set(1, 0).setAngle(Creator.floatInBounds(0, 360))
}
