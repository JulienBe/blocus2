package systems.physic

import com.badlogic.gdx.math.{MathUtils, Matrix4, Rectangle, Vector2}
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d._
import com.badlogic.gdx.utils.Array
import main.Rome
import systems.physic.objects.Box2DObject
import units.briks.Brik

/**
  * Created by julien on 24/01/17.
  */
object Box2DHelper {

  private val vector2 = new Vector2()
  private val debugRenderer = new Box2DDebugRenderer()

  def removeBody(box2DObject: Box2DObject): Unit = Physic.bodyToClean(box2DObject.body)

  def toBoxUnits(f: Float): Float = f / Rome.ppm
  def fromBoxUnits(f: Float): Float = f * Rome.ppm

  def setPos(ship: Brik, x: Float, y: Float): Unit = {
    ship.body.setTransform(toBoxUnits(x), toBoxUnits(y), 0)
  }

  def centerScreenX(box2DObject: Box2DObject): Float = fromBoxUnits(box2DObject.body.getPosition.x)
  def centerScreenY(box2DObject: Box2DObject): Float = fromBoxUnits(box2DObject.body.getPosition.y)

  def debugRender(matrix4: Matrix4): Unit = {
    val renderMatrix = new Matrix4(matrix4)
    renderMatrix.scale(Rome.ppm, Rome.ppm, 1)
    debugRenderer.render(Physic.world, renderMatrix)
  }

  def createPolygon(box2DObject: Box2DObject, vertices: scala.Array[Vector2]): Body = {
    createBody(box2DObject.bodyType(), createPolygon(vertices), box2DObject.category(), box2DObject.mask(), box2DObject)
  }
  def createCircle(box2DObject: Box2DObject, width: Float): Body = {
    createBody(box2DObject.bodyType(), createCircleShape(width), box2DObject.category(), box2DObject.mask(), box2DObject)
  }
  def createRectangle(box2DObject: Box2DObject, rectangle: Rectangle): Body = {
    createBody(box2DObject.bodyType(), createRectangleShape(rectangle), box2DObject.category(), box2DObject.mask(), box2DObject)
  }
  def addAntennas(ship: Brik): Unit = {
    createAntenna(+15f, -15f, ship)
  }
  private def createAntenna(angleMin: Float, angleMax: Float, ship: Brik) = {
    val radius = 8f
    val shape = creatureConeShape(radius)
    val fixture = createFixture(ship.body, shape, ship.category(), ship.mask(), ship)
    fixture.setSensor(true)
    shape.dispose()
  }

  private def creatureConeShape(radius: Float) = {
    val shape = new PolygonShape()
    val vertices = Seq.fill(8)(new Vector2(0, 0))
    for (i <- 1 until 8) {
      val angle = i / (6.0f * 90 * MathUtils.degreesToRadians)
      vertices(i).set(radius * MathUtils.cos(angle), radius * MathUtils.sin(angle))
    }
    shape.set(vertices.toArray)
    shape
  }

  private def createBody(bodyType: BodyType, shape: Shape, category: Short, mask: Short, obj: Object): Body = {
    val b = Physic.world.createBody(createBodyDef(bodyType))
    createFixture(b, shape, category, mask, obj)
    shape.dispose()
    b.setFixedRotation(true)
    b.setAngularDamping(0)
    b.setAngularVelocity(0)
    b.setGravityScale(0)
    b.setLinearDamping(0)
    b
  }
  private def createBodyDef(bodyType: BodyType): BodyDef = {
    val bodyDef = new BodyDef()
    bodyDef.`type` = bodyType
    bodyDef.allowSleep = false
    bodyDef
  }
  private def createFixture(b: Body, shape: Shape, category: Short, mask: Short, obj: Object) = {
    val fixtureDef = new FixtureDef()
    fixtureDef.shape = shape
    fixtureDef.filter.categoryBits = category
    fixtureDef.filter.maskBits = mask
    fixtureDef.isSensor = true
    fixtureDef.density = 0
    fixtureDef.friction = 0
    fixtureDef.restitution = 0
    val fixture = b.createFixture(fixtureDef)
    fixture.setUserData(obj)
    fixture
  }
  private def createCircleShape(width: Float): CircleShape = {
    val shape = new CircleShape()
    shape.setRadius(toBoxUnits(width / 2))
    vector2.set(toBoxUnits(width / 4), toBoxUnits(width / 4))
    shape.setPosition(vector2)
    shape
  }

  private def createRectangleShape(rectangle: Rectangle): Shape = {
    val polygon = new PolygonShape()
    val center = new Vector2(toBoxUnits(rectangle.x + rectangle.width * 0.5f), toBoxUnits(rectangle.y + rectangle.height * 0.5f))
    polygon.setAsBox((rectangle.width * 0.5f) / Rome.ppm, (rectangle.height * 0.5f) / Rome.ppm, center, 0.0f)
    polygon
  }

  private def createPolygon(vertices: scala.Array[Vector2]): Shape = {
    val shape = new PolygonShape()
    vertices.foreach(
      _.scl(toBoxUnits(1))
    )
    shape.set(vertices)
    shape
  }

  // even are supposed to be horizontal and the following is the corresponding vertical
  // will slice the rectangle into 4 shapes
  // Could more efficiently sliced by sharing walls, but seems easier to understand. tbd if it works with polygon shape vertices
  // 7\----------------/5
  // | 8--------------6 |
  // | |              | |
  // | |              | |
  // | 4______________3 |
  // 1/________________\2
  private def getPolygons(r: Rectangle, ppt: Float, offset: Float): Array[Shape] = {
    val x = r.x / ppt
    val y = r.y / ppt
    val w = r.width / ppt
    val h = r.height / ppt
    val p1 = (x,                  y)
    val p2 = (x + w,              y)
    val p3 = (x + w * (1-offset), y + h * offset)
    val p4 = (x + w * offset,     y + h * offset)
    val p5 = (x + w,              y + h)
    val p6 = (x + w * (1-offset), y + h * (1-offset))
    val p7 = (x,                  y + h)
    val p8 = (x + w * offset,     y + h * (1-offset))
    getPolygons(
      scala.Array(
        p1._1, p1._2, p2._1, p2._2, p3._1, p3._2, p4._1, p4._2,
        p2._1, p2._2, p5._1, p5._2, p6._1, p6._2, p3._1, p3._2,
        p5._1, p5._2, p7._1, p7._2, p8._1, p8._2, p6._1, p6._2,
        p7._1, p7._2, p1._1, p1._2, p4._1, p4._2, p8._1, p8._2
      )
    )
  }

  private def getPolygons(vertices: scala.Array[Float]): Array[Shape] = {
    val shapes = new Array[Shape]()
    for (i <- vertices.indices by 8) {
      val polygon = new PolygonShape()
      polygon.set(scala.Array(
        vertices(i), vertices(i + 1), vertices(i + 2), vertices(i + 3),
        vertices(i + 4), vertices(i + 5), vertices(i + 6), vertices(i + 7)
      ))
      shapes.add(polygon)
    }
    shapes
  }
}