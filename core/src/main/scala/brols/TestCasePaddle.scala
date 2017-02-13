package brols

import com.badlogic.gdx.Gdx
import draw.GdxProvider
import systems.physic.Box2DHelper
import units.Paddle
import units.particles.Square

import scala.util.Random

/**
  * Created by julien on 12/02/17.
  */
class TestCasePaddle(gdxProvider: GdxProvider) extends TestCase(gdxProvider) {

  val paddle = new Paddle
  paddle.setPos(3, 2)

  override def render(delta: Float): Unit = {
    super.render(delta)
    if (Gdx.input.justTouched()) {
      addBall(4, 4, -2, -2)
    }
    shapeRenderer.begin()
    val center = V2.getTmp().set(paddle.centerB2D()).scl(Box2DHelper.fromBoxUnits(1))
    shapeRenderer.line(center, V2.get(60, 0).rotate(Paddle.angle1).add(center))
    shapeRenderer.line(center, V2.get(60, 0).rotate(Paddle.angle2).add(center))
    shapeRenderer.line(center, V2.get(60, 0).rotate(Paddle.angle3).add(center))
    shapeRenderer.line(center, V2.get(60, 0).rotate(Paddle.angle4).add(center))
    shapeRenderer.line(center, V2.get(60, 0).rotate(Paddle.angle5).add(center))
    shapeRenderer.line(center, V2.get(60, 0).rotate(Paddle.angle6).add(center))
    shapeRenderer.end()
  }

}