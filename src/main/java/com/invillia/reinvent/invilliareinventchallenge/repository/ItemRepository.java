package com.invillia.reinvent.invilliareinventchallenge.repository;

import com.invillia.reinvent.invilliareinventchallenge.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemRepository extends ElasticsearchRepository<Item, String> {
}
