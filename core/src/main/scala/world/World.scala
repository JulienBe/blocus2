package world

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Array
import main.Rome
import systems.eventhub.events.{Event, JustTouchedEvent}
import systems.eventhub.{EventHub, EventListener}
import units.balls.Ball
import units.briks.Brik
import units.{GameObject, Wall}

/**
  * Created by julien on 28/01/17.
  */
object World extends EventListener {
  private val gameObjects = new Array[GameObject]()

  EventHub.registerForInputs(this)
  Wall.surround(1f, 1f, Rome.size.w - 1f, Rome.size.h - 1f)

  def removeBrik(brik: Brik): Boolean = gameObjects.removeValue(brik, true)

  def act(delta: Float): Unit = {
    for (i <- 0 until gameObjects.size)
      gameObjects.get(i).act(delta)
  }

  def render(spriteBatch: SpriteBatch): Unit = {
    for (i <- 0 until gameObjects.size)
      gameObjects.get(i).draw(spriteBatch)
  }

  override def heyListen(event: Event): Unit = event match {
    case justTouched: JustTouchedEvent => gameObjects.add(Ball.get())
    case _ => unhandled(event)
  }
}