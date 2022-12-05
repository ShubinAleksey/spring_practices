package com.example.clothesshop.Repository;

import com.example.clothesshop.Models.Check;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CheckRepository extends CrudRepository<Check, Long> {

    public List<Check> findByCheckNumber(Integer checkNumber);

    public List<Check> findByCheckNumberContains(Integer checkNumber);
}
