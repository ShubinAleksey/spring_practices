package com.example.demo2.Repository;

import com.example.demo2.Models.GalaxyShutle;
import com.example.demo2.Models.Star;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GalaxyShutleRepository extends CrudRepository<GalaxyShutle, Long> {
    public List<GalaxyShutle> findByName(String name);
    public List<GalaxyShutle> findByNameContains(String name);

    @Query(value = "SELECT * FROM Star WHERE name = ?1", nativeQuery = true)
    List<GalaxyShutle> selectGalaxyShutle(String name);
}
