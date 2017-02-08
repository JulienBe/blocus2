package systems.physic

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType

/**
  * Created by julien on 30/01/17.
  */
trait Box2DObject {
  val body: Body = createBody()
  def createBody(): Body
  def bodyType(): BodyType
  def category(): Short
  def mask(): Short

  def applyForce(vector: Vector2) = {
    body.applyForceToCenter(vector, true)
    println(vector)
  }
}
