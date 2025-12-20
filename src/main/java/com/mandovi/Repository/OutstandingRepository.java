package com.mandovi.Repository;

import com.mandovi.Entity.Outstanding;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OutstandingRepository extends JpaRepository<Outstanding, Integer> {
    void deleteByBalanceAmtLessThanEqual(Double amount);

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.outstanding;", nativeQuery = true)
    void deleteOutstandingAll ();
}
