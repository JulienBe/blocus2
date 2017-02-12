package systems.physic.objects

import brols.V2
import com.badlogic.gdx.math.Vector2

/**
  * Created by julien on 09/02/17.
  */
trait KinematicObject extends Box2DObject {
  val dir = V2.get()
  def applyForce(vector: Vector2) = {
    dir.set(vector)
  }

  def updatePhysic(delta: Float) = {
    body.setTransform(
      body.getPosition.add(dir.x * delta, dir.y * delta), body.getAngle
    )
  }
}
