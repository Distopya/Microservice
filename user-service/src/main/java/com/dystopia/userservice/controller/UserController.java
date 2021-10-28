package com.dystopia.userservice.controller;

import com.dystopia.userservice.core.entity.User;
import com.dystopia.userservice.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> findAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            if (users.size() > 0)
                return ResponseEntity.ok(users);
            else
                return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable("userId") String userId) {
        try {
            Optional<User> user = userService.getUserById(userId);
            return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<User> insertUser(@RequestBody User user) {
        try {
            User userNew = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userNew);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable("userId") String userId, @Valid @RequestBody User user) {
        try {
            Optional<User> userUp = userService.getUserById(userId);
            if (userUp.isEmpty())
                return ResponseEntity.notFound().build();
            user.setId(userId);
            userService.saveUser(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable("userId") String userId) {
        try {
            Optional<User> userDelete = userService.getUserById(userId);
            if (userDelete.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            userService.deleteUser(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
