package uet.k59t.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.k59t.controller.dto.UserDTO;
import uet.k59t.model.User;
import uet.k59t.service.UserService;

/**
 * Created by Long on 11/21/2016.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //create User
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    //find User by id
    @RequestMapping(value = "getuser", method = RequestMethod.GET)
    public UserDTO findUser(@PathVariable("user_id") Long id){
        return userService.getUserById(id);
    }
}
