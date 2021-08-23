package com.invillia.reinvent.challenge.config;

import com.invillia.reinvent.challenge.entities.Product;
import com.invillia.reinvent.challenge.entities.User;
import com.invillia.reinvent.challenge.repositories.ProductRepository;
import com.invillia.reinvent.challenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {


        User u1 = new User(null, "Maria dos Santos");
        User u2 = new User(null, "Fernando do Amaral");

        userRepository.saveAll(Arrays.asList(u1, u2));

        Product prod1 = new Product(null, "Banana", 10.00);
        Product prod2 = new Product(null, "Uva", 5.00);

        productRepository.saveAll(Arrays.asList(prod1, prod2));

    }


}
