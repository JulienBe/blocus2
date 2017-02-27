package systems.world

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Array
import main.Rome
import systems.eventhub.events.Event
import systems.eventhub.{EventHub, EventListener}
import units.balls.Ball
import units.{Paddle, Wall}

/**
  * Created by julien on 28/01/17.
  */
object World extends EventListener {

  private val level = new Level
  private val paddle = new Paddle
  private val balls = new Array[Ball]()
  var time = 0f

  EventHub.registerForInputs(this)
  Wall.surround(1f, 1f, Rome.size.w - 1f, Rome.size.h - 1f)

  def loadLevel(number: Int) = {
    level.load(number)
  }

  def beforeBeginAct(delta: Float) = {
    level.beforeBeginAct(delta)
    if (balls.size == 0)
      balls.add(Ball.get())
    paddle.act(delta)
  }

  def beforeBeginDraw(spriteBatch: SpriteBatch) = {
    for (i <- 0 until level.briks.size)
      level.briks.get(i).draw(spriteBatch)
    for (i <- 0 until balls.size)
      balls.get(i).draw(spriteBatch)
    paddle.draw(spriteBatch)
  }

  def act(delta: Float): Unit = {
    level.act(delta)
    paddle.act(delta)
    time += delta
  }

  def render(spriteBatch: SpriteBatch): Unit = {
    for (i <- 0 until level.briks.size)
      level.briks.get(i).draw(spriteBatch)
    paddle.draw(spriteBatch)
  }

  override def heyListen(event: Event): Unit = event match {
    case _ => unhandled(event)
  }
}