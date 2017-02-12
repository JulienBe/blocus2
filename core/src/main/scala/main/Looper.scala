package main

import brols.TestCase1
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.GL20
import draw.{GdxProvider, Screener}
import main.Rome.setScreen
import systems.physic.{Box2DHelper, Physic}
import world.World

class Looper(gdxProvider: GdxProvider) extends Screener(gdxProvider) with GdxProvider {

  override def render(delta: Float) = {
    inputs
    act(delta)
    draw
    Physic.doPhysicsStep(delta)
  }

  def inputs() = {
    if (Gdx.input.isKeyJustPressed(Keys.J))
      setScreen(new TestCase1(gdxProvider))
  }

  def act(delta: Float) = {
    Rome.time += delta
    World.act(delta)
  }

  private def draw = {
    camera.update()
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    spriteBatch.begin()
    World.render(spriteBatch)
    spriteBatch.end()
    Box2DHelper.debugRender(camera.combined)
  }
}