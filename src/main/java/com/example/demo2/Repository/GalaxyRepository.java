package com.example.demo2.Repository;

import com.example.demo2.Models.Galaxy;
import org.springframework.data.repository.CrudRepository;

public interface GalaxyRepository extends CrudRepository<Galaxy, Long> {
}
