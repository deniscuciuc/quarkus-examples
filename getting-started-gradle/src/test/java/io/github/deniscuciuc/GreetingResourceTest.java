package io.github.deniscuciuc;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
class GreetingResourceTest {
  @Test
  void testHelloEndpoint() {
    given().when().get("/hello").then().statusCode(200).body(is("Hello from Quarkus REST"));
  }

  @Test
  void testGreetingEndpoint() {
    given().when().get("/hello/greeting/Denis").then().statusCode(200).body(is("Hello, Denis!"));
  }
}
