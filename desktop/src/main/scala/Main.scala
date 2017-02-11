package be.julien.agrippa

import com.badlogic.gdx.backends.lwjgl._
import main.Rome

object Main extends App {
    val cfg = new LwjglApplicationConfiguration
    cfg.title = "nn"
    cfg.height = 800
    cfg.width = 480
    cfg.forceExit = false
    new LwjglApplication(Rome, cfg)
}
