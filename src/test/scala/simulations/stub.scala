package simulations

import io.gatling.core.Predef._
import utils.{CommonScenario, config}

import scala.concurrent.duration._

class stub extends Simulation {

  // Общая длительность теста: 20 минут
  // Начальная нагрузка: 5 юзеров/сек
  // Шаг: +1 юзер/сек
  // Длительность ступени: 4 минуты
  // Рампа (разгон) между ступенями: 10 секунд

  // Количество ступеней можно рассчитать: (25 минут - 4 минуты первой ступени) / (4 минуты + 10 сек) = 5 шага

  setUp(
    CommonScenario.scn
      .inject(
        incrementUsersPerSec(1.0)           // Добавляем по 1 юзеру/сек на каждой новой ступени
          .times(1)                         // Сколько всего ступеней (включая первую)
          .eachLevelLasting(60.minutes)      // Длительность каждой полки (ступени)
          .separatedByRampsLasting(10.seconds) // Плавный переход между ступенями
          .startingFrom(4.8)                // Начинаем с 5 юзеров/сек
      )
  ).protocols(config.httpProtocol)
}
