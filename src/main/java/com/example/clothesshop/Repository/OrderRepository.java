package com.example.clothesshop.Repository;

import com.example.clothesshop.Models.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    public List<Order> findByOrderName(String orderName);
    public List<Order> findByOrderNameContains(String orderName);
}
