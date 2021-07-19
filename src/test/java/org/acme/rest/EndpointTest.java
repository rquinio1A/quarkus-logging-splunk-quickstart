package org.acme.rest;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@QuarkusTestResource(SplunkResource.class)
public class EndpointTest {

  static int splunkAPIPort = 0;

  static String dockerHost = null;

  @Test
  public void test() throws InterruptedException {
    assertTrue(splunkAPIPort > 0);
    assertNotNull(dockerHost);
    RestAssured.given().when().get("/greet/splunk").then().statusCode(OK.getStatusCode());
    Thread.sleep(2000);

    // XML REST API - see https://docs.splunk.com/Documentation/Splunk/latest/RESTREF/RESTsearch#search.2Fjobs
    RestAssured.given()
        .request()
        .formParam("search", "search \"Hello splunk\"")
        .formParam("exec_mode", "oneshot")
        //.formParam("output_mode", "json")
        .relaxedHTTPSValidation()
        .auth().basic("admin", "admin123")
        .log().all()
        .post("https://" + dockerHost + ":" + splunkAPIPort + "/services/search/jobs")
        .then().statusCode(200).body(containsString("Hello splunk"));
  }
}
