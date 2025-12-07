package utils

import io.gatling.core.Predef._
import io.gatling.core.feeder.BatchableFeederBuilder
import io.gatling.http.Predef._

object Feeders {

  val user: BatchableFeederBuilder[String] = csv("users.csv").random

  val depart: BatchableFeederBuilder[String] = csv("depart.csv").random

  val arrive: BatchableFeederBuilder[String] = csv("arrive.csv").random


}
