package brols

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import draw.{GdxProvider, Screener}
import systems.physic.{Box2DHelper, Physic}
import units.balls.Ball
import units.briks.Brik
import units.particles.Square

/**
  * Created by julien on 12/02/17.
  */
class TestCase1(gdxProvider: GdxProvider) extends Screener(gdxProvider) with GdxProvider {

  val testBalls = new Array[Ball]()
  val traces = new Array[Square]()
  val square = new Brik
  square.body.setTransform(2, 4, 0)

  override def render(delta: Float) = {
    if (Gdx.input.justTouched()) {
      edgeCollision
      normalCollision
    }
    camera.update()
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    spriteBatch.begin()
    Physic.doPhysicsStep(delta)
    for (i <- 0 until testBalls.size) {
      val b = testBalls.get(i)
      b.act(delta)
      traces.add(Square.get(b.centerX, b.centerY))
    }
    for (i <- 0 until traces.size)
      traces.get(i).draw(spriteBatch)
    spriteBatch.end()
    Box2DHelper.debugRender(camera.combined)
  }

  private def edgeCollision() = {
    val ball = new Ball
    ball.body.setTransform(4.2f, 6f, 0)
    ball.dir.set(-10, -10)
    ball.body.applyForceToCenter(new Vector2(-4, -4), true)
    testBalls.add(ball)
  }

  private def normalCollision() = {
    val ball = new Ball
    ball.body.setTransform(3.8f, 6f, 0)
    ball.dir.set(-10, -10)
    ball.body.applyForceToCenter(new Vector2(-4, -4), true)
    testBalls.add(ball)
  }
}
