package me.artitrack.backend.controller;

import me.artitrack.backend.Greeting;
import me.artitrack.backend.model.User;
import me.artitrack.backend.repository.UserRepository;
import me.artitrack.backend.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Component
@Path("/api")
@Produces("application/json")
public class ApiController {
  private final UserRepository userRepository;

  @Autowired
  public ApiController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GET
  @Path("test")
  public Greeting test() {
    return new Greeting("Hello, how's it going?");
  }

  @GET
  @Path("users")
  public Iterable<User> getUsers() {
    return userRepository.findAll();
  }

  @GET
  @Path("user")
  public User getLoggedUser() {
    JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return user.getUser();
  }
  @GET
  @Path("user/{id}")
  public User getUser(@PathParam("id") String id) {
    return userRepository.findOne(id);
  }

  @POST
  @Path("user/{steam64}")
  public User addUser(@PathParam("steam64") String steam64) {
    return userRepository.save(new User(steam64));
  }

  @GET
  @Path("steam64/{steam64}")
  public Response getUserBySteam64(@PathParam("steam64") String steam64) {
    User user = userRepository.findOne(steam64);
    if (user == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(user).build();
  }


}
