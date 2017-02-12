package systems.physic.objects

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import systems.physic.Box2DHelper

/**
  * Created by julien on 30/01/17.
  */
trait Box2DObject {
  val body: Body = createBody()
  def createBody(): Body
  def bodyType(): BodyType
  def category(): Short
  def mask(): Short
  def centerX: Float = Box2DHelper.centerScreenX(this)
  def centerY: Float = Box2DHelper.centerScreenY(this)
  def center() = body.getWorldCenter
  def setDir(vector: Vector2) = {
    body.setLinearVelocity(vector)
  }
}
