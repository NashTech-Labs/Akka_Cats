import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

import scala.io.StdIn

object AkkaServer {
  implicit val system = ActorSystem(Behaviors.empty, "akka-http")

  def main(args: Array[String]): Unit = {
    val route = get {
      path("hello") {
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "hihihi"))
      } ~ path("bye") {
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "byebyebye"))
      }
    }

    Http().newServerAt("127.0.0.1", 8080).bind(route)

    println(s"Server now online. Please navigate to http://localhost:8080/")
    StdIn.readLine()
  }
}
