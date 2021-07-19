package org.acme.rest;

import org.slf4j.impl.Slf4jLogger;
import org.slf4j.impl.Slf4jLoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@ApplicationScoped
@Path("/")
public class Endpoint {

  private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Endpoint.class);

  @GET
  @Path("/greet/{name}")
  public String greet(@PathParam("name") String name) {
    String greeting = "Hello " + name;
    log.info(greeting);
    return greeting;
  }
}
