package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.controller;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.User;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User saveUser(@RequestBody User user)
    {
        return userService.saveUser(user);
    }

    @GetMapping("/users")
    public List<User> fetchAllUsers()
    {
        return userService.fetchAllUsers();
    }

    @GetMapping("users/{id}")
    public User fetchUserById(@PathVariable("id") Long userId)
    {

        return userService.fetchUserById(userId);
    }
}
