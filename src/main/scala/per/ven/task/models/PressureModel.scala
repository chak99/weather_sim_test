package per.ven.task.models

object PressureModel {

  private val STANDARD_PRESSURE = 101325.0
  private val KELVIN = 273.15
  private val STANDARD_TEMPERATURE = 15 + KELVIN
  private val GAS_CONSTANT = 8.31447
  private val MOLAR = 0.0289644
  private val GRAVITY = 9.80665
  val TEMPERATURE_LAPSE = 0.0065

  def pressureAt(altitudeInMeters: Double, temperatureInCelsius: Double): Double = {
    val temperatureInKelvin = KELVIN + temperatureInCelsius
    val standardPressureWithAltitude = barometricFormula(altitudeInMeters, temperatureInKelvin)
    val pressureWithTemperature = standardPressureWithAltitude * STANDARD_TEMPERATURE / temperatureInKelvin
    toHPa(pressureWithTemperature)
  }

  private def barometricFormula(altitudeInMeters: Double, temperatureInKelvin: Double) = STANDARD_PRESSURE * Math.pow(1 - (TEMPERATURE_LAPSE * altitudeInMeters / STANDARD_TEMPERATURE), (GRAVITY * MOLAR) / (GAS_CONSTANT * TEMPERATURE_LAPSE))

  private def toHPa(pressureInPa: Double) = pressureInPa.round / 100
}
