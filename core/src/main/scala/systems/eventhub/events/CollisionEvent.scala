package systems.eventhub.events

/**
  * Created by julien on 24/01/17.
  */
class CollisionEvent(objA: Object, objB: Object) extends Event
class ShipCollisionEvent(objA: Object, objB: Object) extends CollisionEvent(objA, objB)
