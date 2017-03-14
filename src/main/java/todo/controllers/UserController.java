package todo.controllers;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import todo.exceptions.AccessForbidden;
import todo.exceptions.ResourceNotFoundException;
import todo.models.Todo;
import todo.models.User;
import todo.models.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {

  @Autowired
  private UserDao _userDao;

  public User getUser(String token) {

    User user;

    user = _userDao.getByToken(token);


    if (user == null) {
      throw new AccessForbidden();
    }

    return user;
  }


  @RequestMapping(value="/", method= RequestMethod.GET)
  @ApiImplicitParam()
  public @ResponseBody User getByToken(@RequestHeader("api_key") @ApiParam(hidden = true) String apiKey) {

    return this.getUser(apiKey);

  }

  @RequestMapping(value="/todos", method= RequestMethod.GET)
  @ApiImplicitParam()
  public @ResponseBody List<Todo> getTodos(@RequestHeader("api_key") @ApiParam(hidden = true) String apiKey) {

    User user = this.getUser(apiKey);

    return user.getTodos();

  }

  @RequestMapping(value = "/", method= RequestMethod.POST)
  public @ResponseBody User create(@RequestBody @ApiParam(value  = "User name") String name) {
    User user = new User(name);

     _userDao.create(user);

     return user;

  }

}
