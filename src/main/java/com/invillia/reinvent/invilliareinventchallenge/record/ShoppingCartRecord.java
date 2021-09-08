package com.invillia.reinvent.invilliareinventchallenge.record;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.invillia.reinvent.invilliareinventchallenge.entity.Item;

import java.math.BigDecimal;
import java.util.List;

public record ShoppingCartRecord(Long id,
                                 BigDecimal total,
                                 String name,
                                 Integer qtd){
}
