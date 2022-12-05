package com.example.clothesshop.Repository;

import com.example.clothesshop.Models.SubdivisionReporting;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubdivisionReportingRepository extends CrudRepository<SubdivisionReporting, Long> {
    public List<SubdivisionReporting> findByDocumentName(String documentName);
    public List<SubdivisionReporting> findByDocumentNameContains(String documentName);
}
