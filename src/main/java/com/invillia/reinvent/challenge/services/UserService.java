package com.invillia.reinvent.challenge.services;

import com.invillia.reinvent.challenge.entities.User;
import com.invillia.reinvent.challenge.repositories.UserRepository;
import com.invillia.reinvent.challenge.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User user){
        return repository.save(user);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
