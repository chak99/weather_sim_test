package per.ven.task.etl.extractors

import java.io.{File, IOException}
import java.util.Objects

import com.typesafe.scalalogging.LazyLogging
import per.ven.task.etl.beans.CityGeoData

import scala.io.{BufferedSource, Source}

class FileExtractor(path: String) extends Extractor with LazyLogging {


  override def extract(): Option[List[CityGeoData]] = {

    var result: Option[List[CityGeoData]] = Option.empty

    var resource: Option[BufferedSource] = Option.empty
    try {
      resource = Option(Source.fromFile(new File(path)))
      result = Option(resource.get.getLines()
        .toStream
        .map(buildCityGeoData(_))
        .filter(_.isDefined)
        .map(_.get)
        .toList)

    } catch {
      case e: IOException => {
        logger.error("Got an IOException!", e)
        throw new RuntimeException(e)
      }
    } finally {
      if (resource.isDefined) {
        resource.get.close()
      }
    }
    result
  }

  val buildCityGeoData = (line: String) => {

    var cityGeoData: Option[CityGeoData] = Option.empty

    if (Objects.nonNull(line)) {
      val tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1).map(_.replaceAll("^\"|\"$", "")).toList
      if (tokens.length == 5) {
        logger.warn("Invalid record: {}", line)
      } else {
        try {
          cityGeoData = Option(CityGeoData(tokens(2), tokens(3).toDouble, tokens(4).toDouble, tokens(5).toDouble))

        } catch {
          case e: IOException => logger.warn("Invalid record : {}", line, e)
        }
      }

    } else {
      logger.warn("Invalid record.")
    }
    cityGeoData
  }
}
