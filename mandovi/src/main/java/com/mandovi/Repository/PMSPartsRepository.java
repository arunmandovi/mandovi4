package com.mandovi.Repository;

import com.mandovi.Entity.PMSParts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PMSPartsRepository extends JpaRepository<PMSParts, Integer> {
}
