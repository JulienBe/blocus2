package systems.world

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import systems.gameloop.Looper
import systems.physic.Physic

/**
  * Created by julien on 21/02/17.
  */
object GameLoop extends Enumeration {
   abstract class GameState() {
    def playLoop(delta: Float, spriteBatch: SpriteBatch, loop: Looper): Unit
   }

  val playing = new GameState {
    override def playLoop(delta: Float, spriteBatch: SpriteBatch, loop: Looper): Unit = {
      println("playing")
      World.act(delta)
      Physic.doPhysicsStep(delta)
    }
  }

  val notStarted = new GameState {
    override def playLoop(delta: Float, spriteBatch: SpriteBatch, loop: Looper): Unit = {
      println("not started")
      World.beforeBeginAct(delta)
      spriteBatch.begin()
      World.beforeBeginDraw(spriteBatch)
      spriteBatch.end()
      if (Gdx.input.justTouched()) {
        World.loadLevel(1)
        loop.state = playing
      }
    }
  }

  val dead: GameState = new GameState {
    override def playLoop(delta: Float, spriteBatch: SpriteBatch, loop: Looper): Unit = {
      println("dead")
      World.reset()
      World.beforeBeginAct(delta)
      spriteBatch.begin()
      World.beforeBeginDraw(spriteBatch)
      spriteBatch.end()
      if (Gdx.input.justTouched())
        loop.state = notStarted
    }
  }
}