import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import scala.io.StdIn
import spray.json.DefaultJsonProtocol._

object AkkaServer {
  implicit val system = ActorSystem(Behaviors.empty, "akka-http") //ini
  implicit val userMarshaller = jsonFormat4(User.apply) //initializing marshaller from user
  case class User(userId: Long, name: String, surname: String, Age: Int)
  def main(args: Array[String]): Unit = {

    val route = get { //creating get routes for server
      path("hello") { //https://localhost:8080/hello
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "hihihi"))//getting here string response from server
      } ~ path("user"/ LongNumber) { userId => // passing user Id through query parameters https://localhost:8080/user/100
        complete(User(userId, "Vlad", "Marinychev", 25))//getting here user json response from server
      }
    } ~ post { // creating post routes for server
      path("user" ) {
        entity(as[User]) { user =>
          complete(user)
        }
      }
    }
    //lalalal

    Http().newServerAt("127.0.0.1", 8080).bind(route) //starting our server

    println(s"Server now online. Please navigate to http://localhost:8080/")
    StdIn.readLine()
  }
}
