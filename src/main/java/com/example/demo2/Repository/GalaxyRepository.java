package com.example.demo2.Repository;

import com.example.demo2.Models.Galaxy;
import com.example.demo2.Models.Star;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GalaxyRepository extends CrudRepository<Galaxy, Long> {
    public List<Galaxy> findByName(String name);
    public List<Galaxy> findByNameContains(String name);

    @Query(value = "SELECT * FROM Star WHERE galaxy_mass = ?1", nativeQuery = true)
    List<Star> selectGalaxy(String galaxy_mass);
}
