package per.ven.task.models

import java.time.LocalDate

object AxialTiltModel {

  val EARTH_YEAR = 365.25
  val EARTH_INCLINATION = 23.4
  val EQUINOX = 172

  def onDate(dateTime: LocalDate): Double = {
    val doy = dateTime.getDayOfYear
    val grad = ((doy - EQUINOX) / EARTH_YEAR) * 2 * Math.PI
    Math.cos(grad) * EARTH_INCLINATION
  }
}
