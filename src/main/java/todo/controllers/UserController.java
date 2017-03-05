package todo.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import todo.excaptions.ResourceNotFoundException;
import todo.models.User;
import todo.models.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/user")
public class UserController {

  @Autowired
  private UserDao _userDao;
  

  @RequestMapping(value="/get-by-token/{token}", method= RequestMethod.GET)
  public @ResponseBody User getByToken(@PathVariable String token) {
    User user;
    try {
      user = _userDao.getByToken(token);
    }
    catch(Exception ex) {
        throw new ResourceNotFoundException();
    }

    if (user == null) {
        throw new ResourceNotFoundException();
    }

    return user;

  }

}
