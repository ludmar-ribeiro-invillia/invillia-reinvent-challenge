package com.invillia.reinvent.challenge.repositories;

import com.invillia.reinvent.challenge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
