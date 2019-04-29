package per.ven.task.etl.publishers

import per.ven.task.etl.beans.WeatherReport


trait Publisher {

  def publish(weatherReports: Seq[WeatherReport]): Unit
}
