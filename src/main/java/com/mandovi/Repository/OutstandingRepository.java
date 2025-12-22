package com.mandovi.Repository;

import com.mandovi.Entity.Outstanding;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OutstandingRepository extends JpaRepository<Outstanding, Integer> {
    void deleteByBalanceAmtLessThanEqual(Double amount);

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.outstanding;", nativeQuery = true)
    void deleteOutstandingAll ();

    @Query(value = "SELECT * FROM mandovi.outstanding WHERE bill_no LIKE '%BC%';", nativeQuery = true)
    public List<Outstanding> getCashOutstanding();

    @Query(value = "SELECT * FROM mandovi.outstanding WHERE bill_no LIKE '%BR%';", nativeQuery = true)
    public List<Outstanding> getInvoiceOutstanding();

    @Query(value = "SELECT * FROM mandovi.outstanding WHERE bill_no LIKE '%BI%';", nativeQuery = true)
    public List<Outstanding> getInsuranceOutstanding();

    @Query(value = "SELECT * FROM mandovi.outstanding WHERE bill_no not LIKE '%BI%'" +
            "AND bill_no not LIKE '%BR%' AND bill_no not LIKE '%BC%';", nativeQuery = true)
    public List<Outstanding> getOthersOutstanding();
}
