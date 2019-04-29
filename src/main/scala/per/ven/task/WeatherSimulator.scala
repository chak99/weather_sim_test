package per.ven.task

import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging.LazyLogging
import per.ven.task.etl.WeatherCalculator
import per.ven.task.etl.extractors.FileExtractor
import per.ven.task.etl.publishers.FilePublisher
import per.ven.task.utils.RandomDateGenerator

/**
  * Weather Simulator!
  *
  */
object WeatherSimulator extends LazyLogging {

  def main(args: Array[String]): Unit = {

    val config = ConfigFactory.load()
    run(config)
  }

  def run(config: Config): Unit = {

    val extractor = new FileExtractor(config.getString("etl.extractor_file_path"))
    val publisher = new FilePublisher(config.getString("etl.publisher_file_path"))

    val cities = extractor.extract()
    if (cities.isDefined) {

      val reports = cities.get.map(WeatherCalculator.calculate(_, RandomDateGenerator.randomDate()))
      publisher.publish(reports)

    } else {
      logger.error("Invalid extractor input.")
    }
  }

}
