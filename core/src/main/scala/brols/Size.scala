package brols

import systems.physic.Box2DHelper

/**
  * Created by julien on 22/01/17.
  */
class Size(val w: Float, val h: Float) {
  val hw: Float = w / 2
  val hh: Float = h / 2
  val qw: Float = w / 4
  val qh: Float = h / 4
  val wB2D: Float = Box2DHelper.toBoxUnits(w)
  val hB2D: Float = Box2DHelper.toBoxUnits(h)
  val hwB2D: Float = wB2D / 2
  val hhB2D: Float = hB2D / 2
}
