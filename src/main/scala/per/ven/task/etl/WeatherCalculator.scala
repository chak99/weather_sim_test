package per.ven.task.etl

import java.time.LocalDateTime

import per.ven.task.etl.beans.{CityGeoData, WeatherReport}
import per.ven.task.models._

object WeatherCalculator {

  def calculate(cityGeoData: CityGeoData, dateTime: LocalDateTime): WeatherReport = {

    val relativeLatitude = cityGeoData.latitude - AxialTiltModel.onDate(dateTime.toLocalDate)
    val temperature = TemperatureModel.temperatureAt(relativeLatitude, cityGeoData.altitude, dateTime.toLocalTime)
    val humidity = HumidityModel.humidityAt(relativeLatitude, cityGeoData.longitude)
    val pressure = PressureModel.pressureAt(cityGeoData.altitude, temperature)
    val condition = WeatherConditionModel.conditionFor(temperature, humidity)

    WeatherReport(
      cityGeoData,
      dateTime,
      condition,
      temperature,
      pressure,
      humidity
    )
  }
}
