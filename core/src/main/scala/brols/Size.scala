package brols

import systems.physic.Box2DHelper

/**
  * Created by julien on 22/01/17.
  */
class Size(val w: Float, val h: Float) {
  val hw = w / 2
  val hh = h / 2
  val qw = w / 4
  val qh = h / 4
  val boxhw = Box2DHelper.toBoxUnits(hw)
  val boxhh = Box2DHelper.toBoxUnits(hh)
}
