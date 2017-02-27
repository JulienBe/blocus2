package main

import brols.Size
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.{Game, Gdx}
import draw.{GdxProvider, Textures}
import systems.MyInputProcessor
import systems.eventhub.EventListener
import systems.eventhub.events.Event
import systems.physic.resolvers.{CollisionResolver, ImperialDestroyer}

object Rome extends Game {

  private val unhandledEvent = "Unhandled Event"
  private val unhandledObject = "Unhandled Object"

  val ppm = 80
  var size: Size = _
  val screenCenter = new Vector2(0, 0)

  def updateScreenSizes(): Vector2 = {
    size = new Size(Gdx.graphics.getWidth, Gdx.graphics.getHeight)
    screenCenter.set(size.hw, size.hh)
  }

  override def create(): Unit = {
    setScreen(new Looper(new GdxProvider {}))
    Gdx.input.setInputProcessor(new MyInputProcessor())
    CollisionResolver
    ImperialDestroyer
    Textures.loadAssets()
  }

  def logUnhandledEvent(event: Event, from: EventListener): Unit =
    Gdx.app.debug(Rome.unhandledEvent, from + " :: " + event)
  def log(value: Any, s: String): Unit =
    Gdx.app.debug(unhandledObject, s + " :: " + value)

}