import com.spotify.docker.client.DefaultDockerClient
import com.spotify.docker.client.messages.ContainerConfig
import com.spotify.docker.client.messages.PortBinding
import java.util
import com.spotify.docker.client.messages.HostConfig

object startDocker {
  def apply() :String ={
    val docker = DefaultDockerClient.fromEnv.build
    val ports: Array[String] = Array("8080");
    val portBindings :util.Map[String,util.List[PortBinding]] = new util.HashMap[String,util.List[PortBinding]]()
    for (port <- ports) {
      val hostPorts = new util.ArrayList[PortBinding]
      hostPorts.add(PortBinding.of("0.0.0.0", port))
      portBindings.put(port, hostPorts)
    }

    val hostConfig = HostConfig.builder.portBindings(portBindings).build
    val containerConfig = ContainerConfig.builder()
      .hostConfig(hostConfig)
      .image("mr-build-server").exposedPorts("8080")
      .build()

    val creation = docker.createContainer(containerConfig)
    val id = creation.id
    docker.startContainer(id)
    id
  }
}
object stopDocker {
  def apply(id: String): Unit = {
    val docker = DefaultDockerClient.fromEnv.build
    docker.killContainer(id);
    docker.removeContainer(id);
    docker.close();
  }
}
