package io.github.deniscuciuc.micrometer.quickstart;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.ws.rs.*;

@Path("/example")
@Produces("text/plain")
public class ExampleResource {

  private final MeterRegistry registry;

  public ExampleResource(MeterRegistry registry) {
    this.registry = registry;
  }

  @GET
  @Path("prime/{number}")
  public String isPrime(int number) {
    if (number < 2) {
      registry.counter("prime.number", "result", "false").increment();
      return "false";
    }

    for (int i = 2; i <= Math.sqrt(number); i++) {
      if (number % i == 0) {
        registry.counter("prime.number", "result", "false").increment();
        return "false";
      }
    }

    registry.counter("prime.number", "result", "true").increment();
    return "true";
  }
}
