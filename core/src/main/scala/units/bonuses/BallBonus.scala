package units.bonuses

import com.badlogic.gdx.math.Vector2
import systems.eventhub.EventHub
import units.balls.Ball
import units.briks.Brik

/**
  * Created by julien on 04/03/17.
  */
class BallBonus extends Bonus {


  override def destroy(): Unit = {
    val b = Ball.get()
    b.setPosBox2D(xB2D(), yB2D())
//    EventHub.addBall(b)
  }
}

object BallBonus {
  def get(xB2D: Float, yB2D: Float): BallBonus = {
    val b = new BallBonus()
    b.setDir(Bonus.defaultDir)
    b.setPosBox2D(xB2D, yB2D)
    b
  }
}