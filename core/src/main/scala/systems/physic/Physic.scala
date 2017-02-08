package systems.physic

import box2dLight.RayHandler
import com.badlogic.gdx.graphics.{Color, OrthographicCamera}
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d._
import com.badlogic.gdx.utils.Array

/**
  * Created by julien on 24/01/17.
  */
object Physic {

  val playerCategory: Short = 0x0001
  val otherCategory: Short = 0x0002

  val playerMask: Short = otherCategory
  val otherMask: Short = (otherCategory | playerCategory).toShort

  private val bodiesToClean = new Array[Body]()
  private val bodiesToDeactivate = new Array[Body]()
  private val gravity = new Vector2(0, 0)

  val world = new World(gravity, true)
  world.setContactListener(new CollisionMaster)

  private val rayHandler = new RayHandler(world)
  rayHandler.setAmbientLight(Color.DARK_GRAY)

  private val timestep = 1/60f
  private val velocityIteration = 6
  private val positionIteration = 2

  private var accumulator = 0f

  def render(delta: Float, cam: OrthographicCamera) = {
    rayHandler.setCombinedMatrix(cam)
    rayHandler.updateAndRender()
  }

  def doPhysicsStep(deltaTime: Float) {
    // fixed time step
    // max frame time to avoid spiral of death (on slow devices)
    val frameTime = Math.min(deltaTime, 0.25f)
    accumulator += frameTime
    while (accumulator >= timestep) {
      world.step(timestep, velocityIteration, positionIteration)
      accumulator -= timestep
    }
    doForAllBodies(world.destroyBody(_), bodiesToClean)
    sleepAllBodies(bodiesToDeactivate)
    val bodies = new Array[Body]()
    world.getBodies(bodies)
  }

  def bodyToClean(body: Body) = bodiesToClean.add(body)
  def bodyToSleep(body: Body) = bodiesToDeactivate.add(body)

  private def doForAllBodies(methodToBodies: (Body) => Unit, bodies: Array[Body]) = {
    for (i <- 0 until bodies.size)
      methodToBodies(bodies.get(i))
    bodies.clear()
  }

  private def sleepAllBodies(bodies: Array[Body]) = {
    for (i <- 0 until bodies.size)
      bodies.get(i).setActive(false)
    bodies.clear()
  }
}
