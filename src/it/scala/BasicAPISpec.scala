import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet, HttpPut}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import org.apache.http.util.EntityUtils

class BasicAPISpec extends FlatSpec with Matchers {
  var id :String = ""

  "PUTting a JSON description of a System" should "make the system available to inquire about" in {
    val id = startDocker()
    Thread.sleep(5000)

    val json =
      new StringEntity("""
        {
          "system":{
            "name":"test system",
            "builds":[
              {
                "schedule":"* * * * *",
                "source-control":{"location":"git://blah.com"},
                "steps":[
                  {"ls -ltra"}
                ]
              }
            ]
          }
        }
      """.stripMargin)

    val httpclient = HttpClients.createDefault
    val httpPut = new HttpPut("http://localhost:8080/systems")
    httpPut.setEntity(json);

    val httpGet = new HttpGet("http://localhost:8080/systems/1/builds/1/source-control/location")

    try{
      val putResponse = httpclient.execute(httpPut)
      putResponse.getStatusLine.getStatusCode should be < 300
      putResponse.close

      val getResponse = httpclient.execute(httpGet)
      getResponse.getStatusLine.getStatusCode should be < 300
      val b = new Array[Byte](14);
      getResponse.getEntity().getContent().read(b)
      getResponse.close

      new String(b) shouldBe "git://blah.com"
    }
    finally {
      stopDocker(id)
    }
  }

  "PUTting a JSON description of a System" should "store the system in a local H2 database" in {

  }

  "GETting from the main systems endpoint" should "give us a list of saved systems" in {

  }

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
