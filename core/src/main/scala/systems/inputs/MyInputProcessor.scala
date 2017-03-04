package systems.inputs

import com.badlogic.gdx.{Gdx, InputAdapter}
import systems.eventhub.EventHub

/**
  * Created by julien on 23/01/17.
  */
class MyInputProcessor extends InputAdapter {

  private def y(screenY: Int) = Gdx.graphics.getHeight - screenY

  override def touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = {
    EventHub.mouseMoved(screenX, y(screenY))
    true
  }

  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
    EventHub.justTouched(screenX, y(screenY))
    true
  }
}