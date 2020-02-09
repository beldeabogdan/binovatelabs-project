package com.beldeabogdan.binovate.controllers;


import com.beldeabogdan.binovate.factories.ErrorResponseFactory;
import com.beldeabogdan.binovate.models.User;
import com.beldeabogdan.binovate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/user")
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
        int page;
        int itemsPerPage;
        try {
            page = Integer.parseInt(pageString);
            itemsPerPage = Integer.parseInt(itemsPerPageString);
        } catch (NumberFormatException e) {
            throw ErrorResponseFactory.generic(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Query params page and itemsPerPage should be positive integers"
            );
        }

        return userService.findAll(page, itemsPerPage);
    }
}
