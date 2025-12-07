package utils

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.client.body.multipart.MultipartFormDataRequestBody
import io.gatling.http.request.builder.HttpRequestBuilder
import scala.util.Random

object Actions {

  // Запрос главной страницы
  val mainPage: HttpRequestBuilder = http("GetMainPage")
    .get("/webtours/")

//  Получение сессии из пред ответа
  val getSess: HttpRequestBuilder = http("GetSession")
    .get("/cgi-bin/nav.pl?in=home")
    .check(
      regex("""name="userSession" value="([^"]+)"""").saveAs("userSession")
    )

//  Авторизация
def login: HttpRequestBuilder = http("Login")
  .post("/cgi-bin/login.pl")
  .body(ElFileBody("bodies/login.txt"))
  .check(status.is(200))

  //  Запрос страницы с созданием полета
  val flights: HttpRequestBuilder = http("GetFlights")
    .get("/cgi-bin/reservations.pl?page=welcome")
    .check(status.is(200))

//  Выбор данных для полета
val choiceData: HttpRequestBuilder = http("ChoiceDataFly")
  .post("/cgi-bin/reservations.pl")
  .body(ElFileBody("bodies/choiceData.txt"))
  .check(status.is(200))
  .check(
    regex("""name="outboundFlight" value="([^"]+)"""")
      .findAll
      .saveAs("allOutboundFlights")
  )

  //  Выбор билета из доступных
  val choiceTicket: HttpRequestBuilder = http("ChoiceTicket")
    .post("/cgi-bin/reservations.pl")
    .body(ElFileBody("bodies/choiceTicket.txt"))
    .check(status.is(200))

  //  Покупка билета
  val buyFlights: HttpRequestBuilder = http("BuyFlights")
    .post("/cgi-bin/reservations.pl")
    .body(ElFileBody("bodies/buyFlights.txt"))
    .check(status.is(200))
}
