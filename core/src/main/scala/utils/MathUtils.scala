package utils

/**
  * Created by julien on 15/02/17.
  */
object MyMathUtils {

  def ceil(f: Float, max: Float): Float = {
    if (f > max)
      return max
    if (f < -max)
      return -max
    return f
  }

}
