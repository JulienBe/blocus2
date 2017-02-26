package main.tests

import brols.V2
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.utils.Array
import draw.{GdxProvider, Screener}
import systems.physic.{Box2DHelper, Physic}
import units.balls.Ball
import units.particles.Square

/**
  * Created by julien on 13/02/17.
  */
abstract class TestCase(gdxProvider: GdxProvider) extends Screener(gdxProvider) with GdxProvider {
  val testBalls = new Array[Ball]()
  val traces = new Array[Square]()
  var freeze = false

  override def render(delta: Float) = {
    if (Gdx.input.isKeyJustPressed(Keys.SPACE))
      freeze = !freeze
    camera.update()
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    spriteBatch.begin()
    if (!freeze)
      Physic.doPhysicsStep(delta)
    for (i <- 0 until testBalls.size) {
      val b = testBalls.get(i)
      b.act(delta)
      traces.add(Square.get(b.xScreen + 1, b.yScreen + 1))
    }
    for (i <- 0 until traces.size)
      traces.get(i).draw(spriteBatch)
    spriteBatch.end()
    Box2DHelper.debugRender(camera.combined)
  }

  protected def addBall(x: Float, y: Float, dirX: Float, dirY: Float) = {
    val ball = new Ball
    ball.body.setTransform(x, y, 0)
    ball.setDir(V2.getTmp().set(dirX, dirY))
    testBalls.add(ball)
  }
}
