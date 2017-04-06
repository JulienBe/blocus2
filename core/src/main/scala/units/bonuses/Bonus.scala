package units.bonuses

import brols.{R, Size, V2}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.{Rectangle, Vector2}
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import main.Rome
import systems.physic.{Box2DHelper, Physic}
import systems.physic.objects.Box2DObject
import systems.world.Level
import units.GameObject

/**
  * Created by julien on 04/03/17.
  */
abstract class Bonus extends Box2DObject with GameObject {

  def destroy()

  override def createBody(): Body = Box2DHelper.createRectangle(this, Bonus.rect)
  override def bodyType(): BodyType = Bonus.bodyType
  override def category(): Short = Bonus.category
  override def mask(): Short = Bonus.mask

  override def act(delta: Float): Unit = {}
  override def draw(batch: SpriteBatch): Unit = {}
}

object Bonus {
  def bodyType = BodyType.KinematicBody
  def category = Physic.bonusCategory
  def mask = Physic.bonusMask
  def speed = 1
  val defaultDir: Vector2 = V2.get(0, -1).scl(speed)
  val size = new Size(Rome.size.w / Level.brikPerRow, 20)
  val rect: Rectangle = R.get().set(0, 0, size.w, size.h)
}