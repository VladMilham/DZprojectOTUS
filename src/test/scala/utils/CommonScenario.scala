package utils

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

import scala.util.Random


object CommonScenario {
  val scn: ScenarioBuilder =
    scenario("CommonScenario")

    .exec(Actions.mainPage)

    .exec(Actions.getSess)

    .feed(Feeders.user)
    .exec(Actions.login)

    .exec(Actions.flights)

    .feed(Feeders.depart)
    .feed(Feeders.arrive)

    // Проверка и переролль, если города одинаковые
    .doWhile(session => session("depart").as[String] == session("arrive").as[String]) {
      feed(Feeders.arrive)  // Берем еще раз, пока не разные
    }

    .exec(Actions.choiceData)
//    .exec { session =>
//      val flights = session("allOutboundFlights").as[List[String]]
//      println("allOutboundFlights: " + flights.mkString(", "))
//      session
//    }

    // выбираем рандомный полет
    .exec { session =>
      val allFlights = session("allOutboundFlights").as[List[String]]
      val randomFlight = allFlights(Random.nextInt(allFlights.length))

      session.set("outboundFlight", randomFlight)
    }


    .exec(Actions.choiceTicket)
    .exec(Actions.buyFlights)
    .exec(Actions.mainPage)
}