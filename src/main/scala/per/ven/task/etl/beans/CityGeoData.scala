package per.ven.task.etl.beans

/** *
  *
  * @param name      City name
  * @param latitude  latitude of the city
  * @param longitude longitude of the city
  * @param altitude  altitude of the city
  */
case class CityGeoData(name: String,
                       latitude: Double,
                       longitude: Double,
                       altitude: Double)
