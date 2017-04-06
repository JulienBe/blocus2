package systems.world

import systems.eventhub.EventHub
import units.bonuses.BallBonus
import units.briks.Brik
import utils.Roll

/**
  * Created by julien on 04/03/17.
  */
object BonusMaster {

  private val base = 200

  def roll(brik: Brik) = {
//    if (Roll.int(base) < xp) {
      EventHub.addBonus(() => BallBonus.get(brik.xB2D(), brik.yB2D()))
//    }
  }

}
