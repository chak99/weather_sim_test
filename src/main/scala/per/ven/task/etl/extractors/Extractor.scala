package per.ven.task.etl.extractors

import per.ven.task.etl.beans.CityGeoData

trait Extractor {

  def extract(): Option[List[CityGeoData]]
}
