package com.invillia.reinvent.invilliareinventchallenge.controller;

import com.invillia.reinvent.invilliareinventchallenge.entity.User;

import com.invillia.reinvent.invilliareinventchallenge.record.UserRecord;
import com.invillia.reinvent.invilliareinventchallenge.service.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {

        User u = this.userService.save(user);
        if (u.getId() != null) {
            return new ResponseEntity<UserRecord>(this.mapUserRecord(u), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Erro ao persistir o usuário", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getUsuarioById(@PathVariable("id") Long id)throws Exception {
        Optional<User> usuario = userService.findById(id);
        return usuario.map(value -> ResponseEntity.ok(this.mapUserRecord(usuario.get())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public UserRecord mapUserRecord(User usuario) {

        return new UserRecord(usuario.getNomeCompleto(),
                usuario.getEmail(),
                usuario.getCpf());
    }
}
