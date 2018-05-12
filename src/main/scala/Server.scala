import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.HttpApp

// db = h2
// swagger!
object Server extends HttpApp{
  override def routes: Route =
    path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Hello, World."))
      }
    }
  def main(args :Array[String])= Server.startServer("0.0.0.0", 8080)
}


