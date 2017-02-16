package brols

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Array
import draw.GdxProvider
import systems.eventhub.events.{DestroyBrik, Event}
import systems.eventhub.{EventHub, EventListener}
import units.Paddle
import units.balls.Ball
import units.briks.Brik

/**
  * Created by julien on 12/02/17.
  */
class TestCaseBrikDestruction(gdxProvider: GdxProvider) extends TestCase(gdxProvider) with EventListener {

  EventHub.registerForDestroy(_)

  val paddle: Paddle = Paddle.get()

  val ball: Ball = Ball.get()
  ball.setDir(-1, -1)
  ball.setPosBox2D(Paddle.defaultXB2D + 1, Paddle.yB2D + 1)

  val briks = new Array[Brik]()
  for (i <- 0 until 10) {
    briks.add(Brik.getB2B(i * Brik.size.wB2D, 4))
  }

  override def render(delta: Float): Unit = {
    super.render(delta)
    if (Gdx.input.justTouched()) {
    }
    paddle.act(delta)
    ball.act(delta)
    for (i <- 0 until briks.size) {
      val b = briks.get(i)
      b.act(delta)
    }

    spriteBatch.begin()
    ball.draw(spriteBatch)
    spriteBatch.end()
  }

  override def heyListen(event: Event): Unit = {
    event match {
      case destroyBrik: DestroyBrik => briks.removeValue(destroyBrik.brik, true)
      case _ => unhandled(event)
    }
  }
}