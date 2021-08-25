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


    @Override
    public void run(String... args) throws Exception {




    }


}
