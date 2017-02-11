package world

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Array
import main.Rome
import systems.eventhub.events.{Event, JustTouchedEvent}
import systems.eventhub.{EventHub, EventListener}
import units.{GameObject, Wall}
import units.balls.Ball
import units.briks.Brik

/**
  * Created by julien on 28/01/17.
  */
object World extends EventListener {
  private val gameObjects = new Array[GameObject]()

  EventHub.registerForInputs(this)
  Wall.surround(1f, 1f, Rome.width - 1f, Rome.height - 1f)

  def removeBrik(brik: Brik) = gameObjects.removeValue(brik, true)

  def act(delta: Float) = {
    for (i <- 0 until gameObjects.size)
      gameObjects.get(i).act(delta)
  }

  def render(shapeRenderer: ShapeRenderer) = {
    for (i <- 0 until gameObjects.size)
      gameObjects.get(i).draw(shapeRenderer)
  }

  override def heyListen(event: Event) = event match {
    case justTouched: JustTouchedEvent => gameObjects.add(Ball.get())
    case _ => unhandled(event)
  }
}
