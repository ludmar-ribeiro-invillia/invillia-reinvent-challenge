package com.invillia.reinvent.invilliareinventchallenge.controller;

import com.invillia.reinvent.invilliareinventchallenge.entity.Product;
import com.invillia.reinvent.invilliareinventchallenge.entity.User;
import com.invillia.reinvent.invilliareinventchallenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping (path = "user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> listUsers() {
        Iterable<User> userIterable = userRepository.findAll();
        return StreamSupport.stream(userIterable.spliterator(),false).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> UserOptional = userRepository.findById(id);
        if (UserOptional.isPresent()) {
            return ResponseEntity.ok().body(UserOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
