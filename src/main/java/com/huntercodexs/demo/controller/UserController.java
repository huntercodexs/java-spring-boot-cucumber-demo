package com.huntercodexs.demo.controller;

import com.huntercodexs.demo.model.UserModel;
import com.huntercodexs.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("${api-prefix}/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping({"", "/"})
    public ResponseEntity<Object> all() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping({"/{userId}", "/{userId}/"})
    public ResponseEntity<Object> one(@PathVariable UUID userId) {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Object> create(@RequestBody UserModel userModel) {
        return new ResponseEntity<>(userService.createUser(userModel), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{userId}", "/{userId}/"})
    public ResponseEntity<Object> delete(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping({"/{userId}", "/{userId}/"})
    public ResponseEntity<Object> update(@PathVariable UUID userId, @RequestBody UserModel userModel) {
        return new ResponseEntity<>(userService.updateUser(userId, userModel), HttpStatus.OK);
    }

    @PatchMapping({"/{userId}", "/{userId}/"})
    public ResponseEntity<Object> fix(@PathVariable UUID userId, @RequestBody UserModel userModel) {
        return new ResponseEntity<>(userService.fixUser(userId, userModel), HttpStatus.OK);
    }
}
