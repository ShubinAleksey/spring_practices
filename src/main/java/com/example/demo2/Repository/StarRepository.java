package com.example.demo2.Repository;

import com.example.demo2.Models.Star;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StarRepository extends CrudRepository<Star, Long>{
    public List<Star> findByName(String name);
    public List<Star> findByNameContains(String name);

    @Query(value = "SELECT * FROM Star WHERE class_star = ?1", nativeQuery = true)
    List<Star> selectStars(String class_star);
}