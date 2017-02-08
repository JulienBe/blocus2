package systems.physic

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType

/**
  * Created by julien on 30/01/17.
  */
trait Box2DObject {
  def bodyType(): BodyType
  def category(): Short
  def mask(): Short
}
