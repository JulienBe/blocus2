package main.tests

import com.badlogic.gdx.Gdx
import draw.GdxProvider
import systems.physic.Box2DHelper
import units.briks.Brik

import scala.util.Random

/**
  * Created by julien on 12/02/17.
  */
class TestCase1(gdxProvider: GdxProvider) extends TestCase(gdxProvider) {

  val square = new Brik(2, 4)

  override def render(delta: Float): Unit = {
    super.render(delta)
    if (Gdx.input.justTouched()) {
      normalCollision()
    }

    shapeRenderer.begin()
    for (i <- 0 until testBalls.size) {
      val b = testBalls.get(i)
      shapeRenderer.line(b.centerB2D().scl(Box2DHelper.fromBoxUnits(1)), square.centerB2D().scl(Box2DHelper.fromBoxUnits(1)))
    }
    shapeRenderer.end()
  }

  private def normalCollision() = {
    for (_ <- 0 until 100)
      addBall(2, 2, (Random.nextFloat() - 0.5f) * 4, (Random.nextFloat() -0.5f) * 4)
    for (i <- 15 until 21)
      addBall(1, 4.5f + i / 10f, 2, -2)
    for (i <- 0 until 5)
      addBall(3.5f, 5.5f + i / 10f, -2, -2)
    addBall(Box2DHelper.toBoxUnits(square.xScreen) - 2, Box2DHelper.toBoxUnits(square.yScreen) + 0.2f, 1, 0)
    addBall(Box2DHelper.toBoxUnits(square.xScreen) + 2, Box2DHelper.toBoxUnits(square.yScreen) + 0.2f, -1, 0)
    for (i <- 0 until 10)
      addBall(-0.2f + Box2DHelper.toBoxUnits(square.xScreen) + i / 10f, Box2DHelper.toBoxUnits(square.yScreen) - 2, 0, 1)
    // Top, min 45, max 90
    for (i <- 0 until 10)
      addBall(-0.2f + Box2DHelper.toBoxUnits(square.xScreen) + i / 10f, Box2DHelper.toBoxUnits(square.yScreen) + 2, 0, -1)
  }

}
