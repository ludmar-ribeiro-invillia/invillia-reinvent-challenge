package com.invillia.challenge.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;
import com.invillia.challenge.shoppingcart.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
