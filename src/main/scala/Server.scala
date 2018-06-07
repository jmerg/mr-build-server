import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.HttpApp

import scala.util.parsing.json.JSONObject

// db = h2
// swagger!
object Server extends HttpApp{
  override def routes: Route =
    path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Hello, World."))
      }
    }~
    path("systems"){
      put {
        decodeRequest {
          entity(as[String]) { system =>
            complete {
              "system received"
            }
          }
        }
      }


    }~
    path("systems" / IntNumber / "builds" / IntNumber / "source-control" / "location") {
      (systemId, buildId) =>
        get{
          complete (HttpEntity(ContentTypes.`text/html(UTF-8)`,"git://blah.com"))
        }
    }

  def main(args :Array[String])= Server.startServer("0.0.0.0", 8080)
}


