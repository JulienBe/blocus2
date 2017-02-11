package main

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.{Game, Gdx}
import draw.{GdxProvider, Textures}
import systems.MyInputProcessor
import systems.eventhub.EventListener
import systems.eventhub.events.Event
import systems.physic.CollisionResolver

object Rome extends Game {

  private val unhandledEvent = "Unhandled Event"

  val ppm = 80
  var time = 0f
  var width = 0f
  var height = 0f
  var halfHwidth = 0f
  var halfHeight = 0f
  val screenCenter = new Vector2(halfHwidth, halfHeight)

  def updateScreenSizes() = {
    width = Gdx.graphics.getWidth
    height = Gdx.graphics.getHeight
    halfHwidth = width / 2
    halfHeight = height / 2
    screenCenter.set(halfHwidth, halfHeight)
  }

  override def create() = {
    setScreen(new Looper(new GdxProvider {}))
    Gdx.input.setInputProcessor(new MyInputProcessor())
    CollisionResolver
    Textures.loadAssets()
  }

  def logUnhandledEvent(event: Event, from: EventListener) =
    Gdx.app.debug(Rome.unhandledEvent, from + " :: " + event)

}
