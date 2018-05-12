import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import org.apache.http.util.EntityUtils

class BasicAPISpec extends FlatSpec with Matchers with BeforeAndAfter {
  var id :String = ""

  "A call to the API" should "return something" in {
    val id = startDocker()
    Thread.sleep(5000)
    val httpclient = HttpClients.createDefault
    val httpGet = new HttpGet("http://localhost:8080/hello")
    val response = httpclient.execute(httpGet)

    val b :Array[Byte]= new Array[Byte](13)
    try {
      System.out.println(response.getStatusLine)
      val entity1 = response.getEntity

      entity1.getContent().read(b);
      System.out.println(new String(b))
      EntityUtils.consume(entity1)
    } finally {
      response.close
      stopDocker(id)
    }
    new String(b) shouldBe "Hello, World."
  }
}
