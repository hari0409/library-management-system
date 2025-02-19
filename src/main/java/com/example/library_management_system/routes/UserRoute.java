package com.example.library_management_system.routes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library_management_system.entity.User;
import com.example.library_management_system.entity.UserAddress;
import com.example.library_management_system.misc.GlobalConstant;
import com.example.library_management_system.misc.ResponseObject;
import com.example.library_management_system.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api/v1/user")
public class UserRoute {
    private UserService userService;

    public UserRoute(UserService userService) {
        this.userService = userService;
    }

    // Creating/Update User
    @PostMapping
    public ResponseEntity<ResponseObject<?>> createNewUser(@RequestBody JsonNode userData) {
        String stat = userService.createOrUpdateUser(userData);
        return ResponseEntity.status(201).body(
                new ResponseObject<String>(GlobalConstant.SUCCESS, stat));
    }

    // Get User by Id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<?>> getUserById(@PathVariable Integer id) {
        return ResponseEntity.status(200)
                .body(new ResponseObject<User>(GlobalConstant.SUCCESS, userService.findUserById(id)));
    }

    // Create/Update Address
    @PostMapping("/address")
    public ResponseEntity<ResponseObject<?>> saveOrUpdateAddress(@RequestBody JsonNode address) {
        return ResponseEntity.status(201).body(
                new ResponseObject<User>(GlobalConstant.SUCCESS, userService.createOrUpdateUserAddress(address)));
    }

    // Get User Address from User Id
    @GetMapping("/address/{id}")
    public ResponseEntity<ResponseObject<?>> getUserAddress(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(
                new ResponseObject<UserAddress>(GlobalConstant.SUCCESS, userService.findUserAddressById(id)));
    }

}
