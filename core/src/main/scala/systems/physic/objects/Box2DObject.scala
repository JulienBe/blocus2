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
  def centerB2D() = body.getWorldCenter
  def setDir(vector: Vector2) = body.setLinearVelocity(vector)
  def setPosBox2D(x: Float, y: Float) = body.setTransform(x, y, 0)
  def setPosWorld(x: Float, y: Float) = setPosBox2D(Box2DHelper.toBoxUnits(x), Box2DHelper.toBoxUnits(y))
  def moveWorld(x: Float, y: Float) = {
    println("move : " + x)
    moveBox2D(Box2DHelper.toBoxUnits(x), Box2DHelper.toBoxUnits(y))
  }
  def moveBox2D(x: Float, y: Float) = body.setTransform(body.getTransform.getPosition.x + x, body.getTransform.getPosition.y + y, body.getAngle)
  def getDir() = body.getLinearVelocity
}
