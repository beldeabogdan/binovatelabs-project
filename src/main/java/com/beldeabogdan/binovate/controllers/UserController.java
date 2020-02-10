package com.beldeabogdan.binovate.controllers;


import com.beldeabogdan.binovate.models.User;
import com.beldeabogdan.binovate.services.UserService;
import com.beldeabogdan.binovate.utils.IntegerParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createOne(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public Page<User> getAll(
            @RequestParam(value = "page", defaultValue = "0") String pageString,
            @RequestParam(value = "itemsPerPage", defaultValue = "50") String itemsPerPageString
    ) {
        int page = IntegerParser.parse(pageString);
        int itemsPerPage = IntegerParser.parse(itemsPerPageString);

        return userService.findAll(page, itemsPerPage);
    }
}
