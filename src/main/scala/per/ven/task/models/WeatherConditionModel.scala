package per.ven.task.models

object WeatherConditionModel {

  /**
    * Finds weather condition using temperature and humidity of the city.
    *
    * @param temperature temperature of the city
    * @param humidity    Humidity of the city
    * @return Value from (Sunny, Rain, Snow)
    */
  def conditionFor(temperature: Double, humidity: Double): String = {
    if (temperature < -5 && humidity > 50) return "Snow"
    else if (humidity > 70) return "Rain"
    "Sunny"
  }
}
