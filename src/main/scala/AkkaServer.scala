import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import scala.io.StdIn
import spray.json.DefaultJsonProtocol._
object AkkaServer {
  implicit val system = ActorSystem(Behaviors.empty, "akka-http")
  implicit val userMarshaler = jsonFormat4(User.apply)
  case class User(userId: Long, name: String, surname: String, Age: Int)
  def main(args: Array[String]): Unit = {
    val route = get {
      path("hello") {
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "hihihi"))
      } ~ path("user"/ LongNumber) { userId =>
        complete(User(userId, "Vlad", "Marinychev", 25))
      }
    } ~ post {
      path("user" ){
        entity(as[User]) { user =>
          complete(user)
        }
      }
    }

    Http().newServerAt("127.0.0.1", 8080).bind(route)

    println(s"Server now online. Please navigate to http://localhost:8080/")
    StdIn.readLine()
  }
}
