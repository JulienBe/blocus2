package world

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Array
import main.Rome
import systems.eventhub.events.{Event, JustTouchedEvent}
import systems.eventhub.{EventHub, EventListener}
import units.ships.{Ball, Brik}

/**
  * Created by julien on 28/01/17.
  */
object World extends EventListener {
  private val briks = new Array[Brik]()
  private val balls = new Array[Ball]()

  EventHub.registerForInputs(this)
  Wall.surround(1f, 1f, Rome.width - 1f, Rome.height - 1f)

  def removeBrik(brik: Brik) = briks.removeValue(brik, true)

  def act(delta: Float) = {
    for (i <- 0 until balls.size)
      balls.get(i).act(delta)
  }

  def render(shapeRenderer: ShapeRenderer) = {
    for (i <- 0 until briks.size)
      briks.get(i).draw(shapeRenderer)
    for (i <- 0 until balls.size)
      balls.get(i).draw(shapeRenderer)
  }

  override def heyListen(event: Event) = event match {
    case justTouched: JustTouchedEvent => balls.add(Ball.get())
  }
}
