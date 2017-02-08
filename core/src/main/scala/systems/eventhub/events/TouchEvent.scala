package systems.eventhub.events

/**
  * Created by julien on 23/01/17.
  */
class TouchEvent(val x: Int, val y: Int) extends Event
class TouchedEvent(x: Int, y: Int) extends TouchEvent(x, y)
class JustTouchedEvent(x: Int, y: Int) extends TouchEvent(x, y)

