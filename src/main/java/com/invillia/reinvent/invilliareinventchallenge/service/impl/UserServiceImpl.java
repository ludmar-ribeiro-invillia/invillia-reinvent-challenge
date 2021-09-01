package com.invillia.reinvent.invilliareinventchallenge.service.impl;

import com.invillia.reinvent.invilliareinventchallenge.entity.User;
import com.invillia.reinvent.invilliareinventchallenge.repository.UserRepository;
import com.invillia.reinvent.invilliareinventchallenge.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public Optional<User> findById(Long userId) {
        Optional<User> u = this.userRepository.findById(userId);
        return u;
    }



}
