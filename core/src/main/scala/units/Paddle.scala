package units

import brols.{Size, V2}
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import main.Rome
import systems.physic.objects.Box2DObject
import systems.physic.{Box2DHelper, Physic}
import utils.MyMathUtils

/**
  * Created by julien on 11/02/17.
  */
class Paddle extends GameObject with Box2DObject {

  setPosBox2D(Paddle.defaultXB2D, -Paddle.yB2D)

  def widthB2D(): Float = Paddle.size.wB2D
  def halfWidthB2D(): Float = Paddle.size.hwB2D
  override def createBody(): Body = Box2DHelper.createPolygon(this, Paddle.vertices)
  override def bodyType(): BodyType = Paddle.bodyType
  override def category(): Short = Paddle.category
  override def mask(): Short = Paddle.mask
  override def centerB2D(): Vector2 = V2.getTmp().set(body.getWorldCenter.x + Paddle.pCenterB2D.x, body.getWorldCenter.y)
  override def xScreen(): Float = super.xScreen + Paddle.size.hw

  override def act(delta: Float): Unit = {
    moveScreen(MyMathUtils.ceil(Gdx.input.getX - xScreen, Paddle.speed * delta), (Paddle.y - yScreen()) * delta * Paddle.anchorStrenght)
  }
  override def draw(batch: SpriteBatch): Unit = {}

}

object Paddle {

  val anchorStrenght = 12
  val speed = 1200f
  val yB2D = 2
  val y: Float = Box2DHelper.fromBoxUnits(yB2D)
  val category: Short = Physic.playerCategory
  val mask: Short = Physic.playerMask
  val size = new Size(100, 20)
  val defaultXB2D: Float = Rome.size.hwB2D - size.hwB2D
  val bodyType = BodyType.KinematicBody
  val p1: Vector2 = V2.get(size.w,         0f)
  val p2: Vector2 = V2.get(size.w * 0.92f, size.h)
//  val p3: Vector2 = V2.get(size.w * 0.75f, size.h)
//  val p4: Vector2 = V2.get(size.w - p3.x,  size.h)
  val p5: Vector2 = V2.get(size.w - p2.x,  p2.y)
  val p6: Vector2 = V2.get(0f,             0f)
  val pCenter: Vector2 = V2.get(size.hw,   0)
  val pCenterB2D: Vector2 = V2.get(pCenter.x, pCenter.y).scl(Box2DHelper.toBoxUnits(1))

  val angle1: Float = V2.getTmp(p1).sub(pCenter).angle()
  val angle2: Float = V2.getTmp(p2).sub(pCenter).angle()
//  val angle3: Float = V2.getTmp(p3).sub(pCenter).angle()
//  val angle4: Float = V2.getTmp(p4).sub(pCenter).angle()
  val angle5: Float = V2.getTmp(p5).sub(pCenter).angle()
  val angle6: Float = V2.getTmp(p6).sub(pCenter).angle()
  val angleRangeNormalBounce: Float = angle5 - angle2
  val angleBounceRight: Float = (angle1 + angle2) / 2
  val angleBounceLeft: Float = (angle5 + angle6) / 2

  val vertices = Array(p1, p2, p5, p6)

  def get() = new Paddle()
}