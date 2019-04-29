package per.ven.task.models

import java.time.LocalTime

object TemperatureModel {

  private val TEMPERATURES = Array(35, 25, 20, 15, 5, 0, -10, -30, -40, -50, -50)
  private val MAX = 1.2
  private val MIN = 0.7

  def temperatureAt(latitude: Double, elevation: Double, time: LocalTime): Double = {
    val base = calculateTemperaturePerLatitude(latitude)
    val atElevation = calculateTemperaturePerElevation(base, elevation)
    calculateTemperaturePerTime(atElevation, time.toSecondOfDay / 60)
  }

  private def calculateTemperaturePerTime(temperature: Double, minutesOfDay: Int) = {
    val halfDay = 12 * 60
    val max = if (temperature > 0) temperature * MAX
    else temperature * MIN
    val min = if (temperature > 0) temperature * MIN
    else temperature * MAX
    val degreesPerMinute = Math.abs((max - min) / halfDay)
    max - (halfDay - minutesOfDay) * degreesPerMinute
  }

  private def calculateTemperaturePerElevation(base: Double, elevation: Double) = base - (elevation * PressureModel.TEMPERATURE_LAPSE)

  private def calculateTemperaturePerLatitude(latitude: Double) = {
    val t = Math.min(Math.abs(latitude), 90).toInt
    val i = t / 10
    val d = t % 10
    TEMPERATURES(i) + ((TEMPERATURES(i) - TEMPERATURES(i + 1)) * (d / 10.0))
  }
}
