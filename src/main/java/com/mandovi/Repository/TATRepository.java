package com.mandovi.Repository;

import com.mandovi.Entity.TAT;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TATRepository extends JpaRepository<TAT,Integer> {

    @Transactional
    @Query("""
    SELECT t FROM TAT t
    WHERE (:months IS NULL OR t.month IN (:months))
    AND (:years IS NULL OR t.year IN (:years)) AND (:financialYears IS NULL OR t.financialYear IN (:financialYears))
    """)
    public List<TAT> getTATByMonthYear (@Param("months") List<String> months, @Param("years") List<String> years,
                                        @Param("financialYears") List<String> financialYears);

    @Transactional
    @Modifying
    @Query("DELETE FROM TAT t WHERE t.month = :month AND t.year = :year")
    void deleteByMonthYear(@Param("month") String month, @Param("year") String year );

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.tat;", nativeQuery = true)
    void deleteTATAll ();

}
