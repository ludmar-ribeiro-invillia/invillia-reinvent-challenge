package com.invillia.reinventchallenge.shoppingcart.repository;

import com.invillia.reinventchallenge.shoppingcart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
