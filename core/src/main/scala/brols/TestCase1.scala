package brols

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.GL20
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
  var freeze = false

  override def render(delta: Float) = {
    if (Gdx.input.justTouched()) {
      normalCollision
    }
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
      traces.add(Square.get(b.centerX, b.centerY))
    }
    for (i <- 0 until traces.size)
      traces.get(i).draw(spriteBatch)
    spriteBatch.end()
    shapeRenderer.begin()
    for (i <- 0 until testBalls.size) {
      val b = testBalls.get(i)
//      shapeRenderer.line(b.center().scl(Box2DHelper.fromBoxUnits(1)), square.center().scl(Box2DHelper.fromBoxUnits(1)))
    }
    shapeRenderer.end()
    Box2DHelper.debugRender(camera.combined)
  }

  private def edgeCollision() = {
    addBall(4.2f, 6f, -4, -4)
  }

  private def normalCollision() = {
//    for (i <- 0 until 100)
//      addBall(2, 2, (Random.nextFloat() - 0.5f) * 4, (Random.nextFloat() -0.5f) * 4)
    for (i <- 15 until 21)
      addBall(1, 4.5f + i / 10f, 2, -2)
    for (i <- 0 until 5)
      addBall(3.5f, 5.5f + i / 10f, -2, -2)
    addBall(Box2DHelper.toBoxUnits(square.centerX) - 2, Box2DHelper.toBoxUnits(square.centerY) + 0.2f, 1, 0)
    addBall(Box2DHelper.toBoxUnits(square.centerX) + 2, Box2DHelper.toBoxUnits(square.centerY) + 0.2f, -1, 0)
//    for (i <- 0 until 10)
//      addBall(-0.2f + Box2DHelper.toBoxUnits(square.centerX) + i / 10f, Box2DHelper.toBoxUnits(square.centerY) - 2, 0, 1)
    // Top, min 45, max 90
    for (i <- 0 until 10)
      addBall(-0.2f + Box2DHelper.toBoxUnits(square.centerX) + i / 10f, Box2DHelper.toBoxUnits(square.centerY) + 2, 0, -1)
  }

  private def addBall(x: Float, y: Float, dirX: Float, dirY: Float) = {
    val ball = new Ball
    ball.body.setTransform(x, y, 0)
    ball.setDir(V2.getTmp().set(dirX, dirY))
    testBalls.add(ball)
  }
}
