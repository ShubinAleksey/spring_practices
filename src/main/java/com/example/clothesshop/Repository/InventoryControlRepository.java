package com.example.clothesshop.Repository;

import com.example.clothesshop.Models.InventoryControl;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InventoryControlRepository extends CrudRepository<InventoryControl, Long> {
    public List<InventoryControl> findByDocumentName(String documentName);
    public List<InventoryControl> findByDocumentNameContains(String documentName);
}
