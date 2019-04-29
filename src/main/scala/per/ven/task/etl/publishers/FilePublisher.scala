package per.ven.task.etl.publishers

import java.io.{File, IOException, PrintWriter}

import com.typesafe.scalalogging.LazyLogging
import per.ven.task.etl.beans.WeatherReport

class FilePublisher(path: String) extends Publisher with LazyLogging {

  override def publish(weatherReports: Seq[WeatherReport]): Unit = {

    var writer: Option[PrintWriter] = Option.empty
    try {
      writer = Option(new PrintWriter(new File(path)))

      weatherReports.map(buildEntry(_))
        .foreach(writer.get.write(_))

    } catch {
      case e: IOException => {
        logger.error("Got an IOException!", e)
        throw new RuntimeException(e)
      }
    } finally {
      if (writer.isDefined) {
        writer.get.close()
      }
    }
  }

  val buildEntry = (weatherReport: WeatherReport) => {

    s"${weatherReport.cityGeoData.name}" +
      s"|${weatherReport.cityGeoData.latitude},${weatherReport.cityGeoData.longitude},${weatherReport.cityGeoData.altitude.toInt}" +
      s"|${weatherReport.localTime}" +
      s"|${weatherReport.conditions}" +
      f"|${weatherReport.temperature}%+.1f" +
      f"|${weatherReport.pressure}%.1f" +
      s"|${weatherReport.humidity.toInt}\n"
  }
}
