package com.example.clothesshop.Repository;

import com.example.clothesshop.Models.ColorProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ColorProductRepository extends CrudRepository<ColorProduct, Long> {

    public List<ColorProduct> findByColorName(String colorName);
    public List<ColorProduct> findByColorNameContains(String colorName);
}
