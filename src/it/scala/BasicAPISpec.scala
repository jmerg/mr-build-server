import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import com.spotify.docker.client.DefaultDockerClient
import com.spotify.docker.client.messages.ContainerConfig

//https://github.com/spotify/docker-client

class BasicAPISpec extends FlatSpec with Matchers with BeforeAndAfter {
  val docker = DefaultDockerClient.fromEnv.build
  var id :String = ""
  before{
    val containerConfig = ContainerConfig.builder()
      .image("mr-build-server")
      .cmd("sh", "-c", "while :; do sleep 1; done")
      .build()

    val creation = docker.createContainer(containerConfig)
    val id = creation.id
    docker.startContainer(id)
  }
  after{
    docker.killContainer(id);
    docker.removeContainer(id);
    docker.close();
  }
  "A call to the API" should "return something" in {

  }
}
