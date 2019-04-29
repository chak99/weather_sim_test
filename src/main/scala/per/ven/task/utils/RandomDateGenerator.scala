package per.ven.task.utils

import java.time.temporal.ChronoUnit
import java.time.{LocalDateTime, ZoneOffset}

object RandomDateGenerator {

  def randomDate(): LocalDateTime = {

    val now = LocalDateTime.now
    val beginTime = now.minus(6, ChronoUnit.MONTHS).toEpochSecond(ZoneOffset.UTC)
    val endTime = now.plus(6, ChronoUnit.MONTHS).toEpochSecond(ZoneOffset.UTC)

    val diff = endTime - beginTime + 1
    val randomTimestamp = beginTime + (Math.random * diff).toLong

    LocalDateTime.ofEpochSecond(randomTimestamp, 0, ZoneOffset.UTC)
  }
}
