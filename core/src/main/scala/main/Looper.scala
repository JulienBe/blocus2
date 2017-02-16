package main

import brols.{TestCase1, TestCaseBrikDestruction, TestCasePaddle}
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.GL20
import draw.{GdxProvider, Screener}
import main.Rome.setScreen
import systems.physic.{Box2DHelper, Physic}
import world.World

class Looper(gdxProvider: GdxProvider) extends Screener(gdxProvider) with GdxProvider {

  override def render(delta: Float): Unit = {
    inputs()
    act(delta)
    draw()
    Physic.doPhysicsStep(delta)
  }

  def inputs(): Unit = {
    if (Gdx.input.isKeyJustPressed(Keys.J))
      setScreen(new TestCase1(gdxProvider))
    if (Gdx.input.isKeyJustPressed(Keys.K))
      setScreen(new TestCasePaddle(gdxProvider))
    if (Gdx.input.isKeyJustPressed(Keys.L))
      setScreen(new TestCaseBrikDestruction(gdxProvider))
  }

  def act(delta: Float): Unit = {
    Rome.time += delta
    World.act(delta)
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