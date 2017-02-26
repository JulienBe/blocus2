package systems.world

import com.badlogic.gdx.maps.MapObjects
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.Array
import main.Rome
import units.briks.Brik

/**
  * Created by julien on 22/02/17.
  */
class Level {

  val briks = new Array[Brik]()

  def load(number: Int) = {
    val map = new TmxMapLoader().load("levels/level" + number + ".tmx")
    val briksObjects = map.getLayers.get(Level.brikLayer).getObjects
    for (i <- 0 until briksObjects.getCount)
      processMapObject(briksObjects, i)
  }

  private def processMapObject(briksObjects: MapObjects, i: Int) = {
    briksObjects.get(i) match {
      case rect: RectangleMapObject => addBrik(rect.getRectangle)
      case _ => Rome.log(this, "unhandled object in level")
    }
  }

  def addBrik(rect: Rectangle) = {
    val col = Level.col(rect.x)
    val row = Level.row(rect.y)
    briks.add(Brik.getScreen(Level.getBrikX(col), Level.getBrikY(row)))
  }
}

object Level {

  val brikLayer = "briks"
  val widthDiv = 32f
  val heightDiv = 16f
  val brikPerRow = 10
  val brikYLimit = Rome.size.h * 0.5f

  def row(y: Float): Int = (y / heightDiv).toInt
  def col(x: Float): Int = (x / widthDiv).toInt
  def getBrikX(col: Int) = Brik.size.w * col
  def getBrikY(row: Int) = brikYLimit + (Brik.size.h * row)
}