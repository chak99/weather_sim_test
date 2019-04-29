package per.ven.task.models

import java.util.Random

object HumidityModel {
  private val generator = new Random()

  def humidityAt(latitude: Double, longitude: Double): Double = generator.nextInt(70) + 20.0
}
