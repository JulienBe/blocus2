package main

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.GL20
import draw.{GdxProvider, Screener}
import main.Rome.setScreen
import main.tests.{TestCase1, TestCaseBrikDestruction, TestCasePaddle}
import systems.physic.{Box2DHelper, Physic}
import systems.world.{GameState, World}

class Looper(gdxProvider: GdxProvider) extends Screener(gdxProvider) with GdxProvider {

  var state = GameState.NotStarted

  override def render(delta: Float): Unit = {
    state match {
      case GameState.NotStarted => notStartedLoop(delta)
      case GameState.Playing => playingLoop(delta)
      case GameState.Paused => pausedLoop(delta)
    }
    inputs()
    draw()
  }

  def notStartedLoop(delta: Float) = {
    World.beforeBeginAct(delta)
    spriteBatch.begin()
    World.beforeBeginDraw(spriteBatch)
    spriteBatch.end()
  }
  def playingLoop(delta: Float) = {
    act(delta)
  }
  def pausedLoop(delta: Float) = {}

  def inputs(): Unit = {
    if (Gdx.input.isKeyJustPressed(Keys.J))
      setScreen(new TestCase1(gdxProvider))
    if (Gdx.input.isKeyJustPressed(Keys.K))
      setScreen(new TestCasePaddle(gdxProvider))
    if (Gdx.input.isKeyJustPressed(Keys.L))
      setScreen(new TestCaseBrikDestruction(gdxProvider))
    if (Gdx.input.isKeyJustPressed(Keys.S))
      World.loadLevel(1)
    if (Gdx.input.justTouched())
      state = GameState.Playing
  }

  def act(delta: Float): Unit = {
    World.act(delta)
    Physic.doPhysicsStep(delta)
  }

  private def draw() = {
    camera.update()
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    spriteBatch.begin()
    World.render(spriteBatch)
    spriteBatch.end()
    Box2DHelper.debugRender(camera.combined)
  }
}