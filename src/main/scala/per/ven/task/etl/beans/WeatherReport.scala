package per.ven.task.etl.beans

import java.time.LocalDateTime

/** *
  *
  * @param cityGeoData cityGeoData
  * @param localTime   local time
  * @param conditions  weather condition [rainy/snow/sunny]
  * @param temperature temperature of the location
  * @param pressure    pressure of the location
  * @param humidity    humidity of the location
  */
case class WeatherReport(cityGeoData: CityGeoData,
                         localTime: LocalDateTime,
                         conditions: String,
                         temperature: Double,
                         pressure: Double,
                         humidity: Double)

/** *
  *
  * @param latitude  latitude
  * @param longitude longitude
  * @param elevation elevation
  */
case class GeoLocation(latitude: Double, longitude: Double, elevation: Long)
