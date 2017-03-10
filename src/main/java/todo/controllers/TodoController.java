package todo.controllers;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import todo.exceptions.AccessForbidden;
import todo.exceptions.ResourceNotFoundException;
import todo.models.Todo;
import todo.models.TodoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import todo.models.User;
import todo.models.UserDao;

@Controller
@RequestMapping(value="/todo")
public class TodoController {

    @Autowired
    private TodoDao _todoDao;

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

    private Todo setResolution(User user, Long id, boolean resolved) {
        Todo todo = _todoDao.find(id);

        if (user.getId() != todo.getUser().getId()) {
            throw new AccessForbidden();
        }


        todo.setResolved(resolved);

        _todoDao.update(todo);

        return todo;
    }

    @RequestMapping(value = "/mark-not-done", method = RequestMethod.POST)
    @ApiImplicitParam()
    public @ResponseBody
    Todo markNotDone(@RequestHeader("api_key") @ApiParam(hidden = true) String apiKey, @RequestBody @ApiParam(value  = "Todo id", required = true) Long id) {
        if (apiKey.length() == 0) {
            throw new AccessForbidden();
        }

        User user = this.getUser(apiKey);

        return this.setResolution(user, id, false);

    }

    @RequestMapping(value = "/mark-done", method = RequestMethod.POST)
    @ApiImplicitParam()
    public @ResponseBody
    Todo markDone(@RequestHeader("api_key") @ApiParam(hidden = true) String apiKey, @RequestBody @ApiParam(value  = "Todo id", required = true) Long id) {
        if (apiKey.length() == 0) {
            throw new AccessForbidden();
        }

        User user = this.getUser(apiKey);

        return this.setResolution(user, id, true);

    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    @ApiImplicitParam()
    public @ResponseBody
    Todo create(@RequestHeader("api_key") @ApiParam(hidden = true) String apiKey, @RequestBody @ApiParam(value  = "Todo content", required = true) String text) {

        if (apiKey.length() == 0) {
            throw new AccessForbidden();
        }

        User user = this.getUser(apiKey);

        return _todoDao.createWithRelation(user, text);


    }

}
