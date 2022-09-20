import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer

import java.net.URLEncoder
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt


object AkkaClient {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  import system.dispatcher

  val source = """
                 |object SimpleApp {
                 |  def main(args: Array[String]): Unit println(123)
                 |}
  """

  val request = HttpRequest(
    method = HttpMethods.POST,
    uri = "http://markup.su/api/highlighter",
    entity = HttpEntity(
      ContentTypes.`application/x-www-form-urlencoded`,
      s"source=${URLEncoder.encode(source,"UTF-8")}&language=scala&theme=Sunburst"
    )
  )

  def sendRequest(): Future[String] = {
    val responseF = Http().singleRequest(request)
    responseF.flatMap { response =>
      response.entity.toStrict(5.seconds).map(_.data.utf8String)
    }
  }

  def main(args: Array[String]): Unit = {
    sendRequest().foreach(println)
  }
}
