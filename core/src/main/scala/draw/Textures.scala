package draw

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.TextureAtlas

/**
  * Created by julien on 11/02/17.
  */
object Textures {

  private val assetManager: AssetManager = new AssetManager()
  private val atlas = new TextureAtlas(Gdx.files.internal("blocus2.pack"))
  var ball = atlas.findRegion("ball")
  var square = atlas.findRegion("square")

  def loadAssets() = {

  }
}
