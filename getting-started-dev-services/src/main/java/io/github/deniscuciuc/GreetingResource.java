package io.github.deniscuciuc;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;
import org.jboss.resteasy.reactive.RestQuery;

@Path("/hello")
public class GreetingResource {

  @GET
  @Transactional
  @Produces(MediaType.TEXT_PLAIN)
  public String hello(@RestQuery String name) {
    if (name == null) {
      return "Hello from Quarkus REST";
    }

    Greeting greeting = new Greeting();
    greeting.name = name;
    greeting.persist();
    return "Hello from Quarkus REST" + " " + name;
  }

  @GET
  @Path("/names")
  @Produces(MediaType.TEXT_PLAIN)
  public String names() {
    List<Greeting> greetings = Greeting.listAll();
    String names = greetings.stream().map(g -> g.name).collect(Collectors.joining(", "));
    return "I've said hello to: " + names;
  }
}
