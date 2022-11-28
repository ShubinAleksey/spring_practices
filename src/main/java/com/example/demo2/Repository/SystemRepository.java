package com.example.demo2.Repository;

import com.example.demo2.Models.Star;
import com.example.demo2.Models.System;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SystemRepository extends CrudRepository<System, Long> {
    public List<System> findByName(String name);
    public List<System> findByNameContains(String name);

    @Query(value = "SELECT * FROM Star WHERE system_mass = ?1", nativeQuery = true)
    List<System> selectSystem(String system_mass);
}
