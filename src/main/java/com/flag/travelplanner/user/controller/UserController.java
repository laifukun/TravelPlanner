package com.flag.travelplanner.user.controller;


import com.flag.travelplanner.user.entity.User;
import com.flag.travelplanner.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/register")
    @ResponseStatus(value= HttpStatus.CREATED)
    public void register(@RequestBody User user) {
        userService.register(user);
    }

    @GetMapping(value="/{user_id}")
    @ResponseBody
    public User getUserById(@PathVariable("user_id") long userId) {
        return userService.getUserById(userId);
    }
}
