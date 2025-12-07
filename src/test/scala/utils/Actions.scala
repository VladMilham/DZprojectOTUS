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
    .formParam("userSession", "#{userSession}")
    .formParam("username", "#{login}")
    .formParam("password", "#{pass}")
    .formParam("login.x", "61")
    .formParam("login.y", "7")
    .formParam("JSFormSubmit", "off")
    .check(status.is(200))

//  Запрос страницы с созданием полета
  val flights: HttpRequestBuilder = http("GetFlights")
    .get("/cgi-bin/reservations.pl?page=welcome")
    .check(status.is(200))

//  Выбор данных для полета
  val choiceData: HttpRequestBuilder = http("ChoiceDataFly")
    .post("/cgi-bin/reservations.pl")
    .formParam("advanceDiscount", "0")
    .formParam("depart", "#{depart}")
    .formParam("departDate", "12/08/2025")
    .formParam("arrive", "#{arrive}")
    .formParam("returnDate", "12/09/2025")
    .formParam("numPassengers", "1")
    .formParam("seatPref", "None")
    .formParam("seatType", "Coach")
    .formParam("findFlights.x", "39")
    .formParam("findFlights.y", "9")
    .formParam("roundtrip", "on")
    .check(status.is(200))
    .check(
      regex("""name="outboundFlight" value="([^"]+)"""")
        .findAll
        .saveAs("allOutboundFlights")
    )

//  Выбор билета из доступных
  val choiceTicket: HttpRequestBuilder = http("ChoiceTicket")
    .post("/cgi-bin/reservations.pl")
    .formParam("outboundFlight", "#{outboundFlight}")
    .formParam("numPassengers", "1")
    .formParam("advanceDiscount", "0")
    .formParam("seatType", "Coach")
    .formParam("seatPref", "None")
    .formParam("reserveFlights.x", "59")
    .formParam("reserveFlights.y", "6")
    .check(status.is(200))

//  Покупка билета
  val buyFlights: HttpRequestBuilder = http("BuyFlights")
    .post("/cgi-bin/reservations.pl")
    .formParam("firstName", "sdgjlks")
    .formParam("lastName", "dsklfdjjs")
    .formParam("address1", "dskjddkjsl")
    .formParam("address2", "dsfjddkjsdka")
    .formParam("pass1", "sdgjlks dsklfdjjs")
    .formParam("creditCard", "#{card}")
    .formParam("expDate", "")
    .formParam("oldCCOption", "")
    .formParam("numPassengers", "1")
    .formParam("seatType", "Coach")
    .formParam("seatPref", "None")
    .formParam("outboundFlight", "#{outboundFlight}")
    .formParam("advanceDiscount", "0")
    .formParam("returnFlight", "")
    .formParam("JSFormSubmit", "off")
    .formParam("buyFlights.x", "57")
    .formParam("buyFlights.y", "14")
    .formParam(".cgifields", "saveCC")
    .check(status.is(200))
}
