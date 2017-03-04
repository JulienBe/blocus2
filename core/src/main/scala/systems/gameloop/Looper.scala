package systems.gameloop

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.GL20
import draw.{GdxProvider, Screener}
import main.Rome
import main.Rome.setScreen
import main.tests.{TestCase1, TestCaseBrikDestruction, TestCasePaddle}
import systems.eventhub.events.{Event, GameLost}
import systems.eventhub.{EventHub, EventListener}
import systems.physic.Box2DHelper
import systems.world.GameLoop.GameState
import systems.world.{GameLoop, World}

class Looper(gdxProvider: GdxProvider) extends Screener(gdxProvider) with GdxProvider with EventListener {

  EventHub.registerForGame(this)
  var state: GameState = GameLoop.notStarted

  override def render(delta: Float): Unit = {
    state.playLoop(delta, spriteBatch, this)
    inputs()
    draw()
  }

  def inputs(): Unit = {
    if (Gdx.input.isKeyJustPressed(Keys.J))
      setScreen(new TestCase1(gdxProvider))
    if (Gdx.input.isKeyJustPressed(Keys.K))
      setScreen(new TestCasePaddle(gdxProvider))
    if (Gdx.input.isKeyJustPressed(Keys.L))
      setScreen(new TestCaseBrikDestruction(gdxProvider))
  }

  private def draw() = {
    camera.update()
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    spriteBatch.begin()
    World.render(spriteBatch)
    spriteBatch.end()
    Box2DHelper.debugRender(camera.combined)
  }

  override def heyListen(event: Event): Unit = event match {
    case lost: GameLost => state = GameLoop.dead
    case _ => Rome.logUnhandledEvent(event, this)
  }
}