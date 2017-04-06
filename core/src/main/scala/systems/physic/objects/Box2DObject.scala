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

  def xScreen(): Float = Box2DHelper.centerScreenX(this)
  def yScreen(): Float = Box2DHelper.centerScreenY(this)
  def xB2D(): Float = body.getPosition.x
  def yB2D(): Float = body.getPosition.y
  def centerB2D(): Vector2 = body.getWorldCenter

  def setPosBox2D(x: Float, y: Float): Unit = body.setTransform(x, y, 0)
  def setPosBox2D(vector: Vector2): Unit = setPosBox2D(vector.x, vector.y)
  def setPosScreen(x: Float, y: Float): Unit = setPosBox2D(Box2DHelper.toBoxUnits(x), Box2DHelper.toBoxUnits(y))

  def moveScreen(x: Float, y: Float): Unit = moveBox2D(Box2DHelper.toBoxUnits(x), Box2DHelper.toBoxUnits(y))
  def moveBox2D(x: Float, y: Float): Unit = body.setTransform(body.getTransform.getPosition.x + x, body.getTransform.getPosition.y + y, body.getAngle)

  def setDir(vector: Vector2): Unit = setDir(vector.x, vector.y)
  def setDir(x: Float, y: Float): Unit = body.setLinearVelocity(x, y)
  def getDir(): Vector2 = body.getLinearVelocity
}
