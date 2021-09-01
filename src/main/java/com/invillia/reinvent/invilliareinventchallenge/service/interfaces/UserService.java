package com.invillia.reinvent.invilliareinventchallenge.service.interfaces;
import com.invillia.reinvent.invilliareinventchallenge.entity.User;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long userId);

    User save(User user);

}
