package io.github.deniscuciuc;

import static org.jboss.resteasy.reactive.RestResponse.Status.CREATED;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import java.util.List;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/fruits")
@ApplicationScoped
public class FruitResource {

  @GET
  public Uni<List<Fruit>> get() {
    return Fruit.listAll();
  }

  @GET
  @Path("/{id}")
  public Uni<Fruit> getSingle(Long id) {
    return Fruit.findById(id);
  }

  @GET
  @Path("/count")
  public Uni<Long> count() {
    return Fruit.count();
  }

  @POST
  public Uni<RestResponse<Fruit>> create(Fruit fruit) {
    return Panache.withTransaction(fruit::persist).replaceWith(RestResponse.status(CREATED, fruit));
  }

  @PUT
  @Path("/{id}")
  public Uni<RestResponse<Fruit>> update(Fruit fruit) {
    return Panache.withTransaction(fruit::persist).replaceWith(RestResponse.ok(fruit));
  }

  @DELETE
  @Path("/{id}")
  public Uni<RestResponse<Long>> delete(Long id) {
    return Panache.withTransaction(() -> Fruit.deleteById(id)).replaceWith(RestResponse.ok(id));
  }
}
