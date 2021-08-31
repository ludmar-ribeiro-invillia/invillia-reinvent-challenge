package com.invillia.reinvent.shoppingcart.repository;

import com.invillia.reinvent.shoppingcart.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,String> {
}
