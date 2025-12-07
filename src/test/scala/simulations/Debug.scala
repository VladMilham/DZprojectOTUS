package simulations

import io.gatling.core.Predef._
import utils.{CommonScenario, config}

import scala.concurrent.duration._

class Debug extends Simulation {
  setUp(
    CommonScenario.scn
      .inject(
//        rampUsers(6) during (6.seconds), // разгоняем до 6 юзеров за 6 секунд
//        constantUsersPerSec(6) during (1.minutes) // держим 6 *пользователей в секунду* 3 минуты
        atOnceUsers(1)
      )
      .protocols(config.httpProtocol)
  )
}

