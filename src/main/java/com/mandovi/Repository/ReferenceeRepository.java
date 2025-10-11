package com.mandovi.Repository;

import com.mandovi.DTO.ReferenceeSummaryDTO;
import com.mandovi.Entity.Referencee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReferenceeRepository extends JpaRepository<Referencee, Integer> {

    @Transactional
    @Query("SELECT r FROM Referencee r WHERE r.month = :month AND r.year = :year")
    public List<Referencee> getReferenceeByMonthYear(@Param("month") String month,@Param("year") String year);

    //Group By city
    @Query("""
            SELECT new com.mandovi.DTO.ReferenceeSummaryDTO(
            r.city,
            null,
            SUM(r.referencee),
            SUM(r.enquiry),
            SUM(r.booking),
            SUM(r.invoice),
            SUM(r.booking) * 100.00 / NULLIF(SUM(r.enquiry), 0),
            SUM(r.invoice) * 100.00 / NULLIF(SUM(r.enquiry), 0),
            SUM(r.invoice) * 100.00 / NULLIF(SUM(r.booking), 0)
            )
            FROM Referencee r
            WHERE (:month IS NULL OR r.month = :month)
            AND (:qtrWise IS NULL OR r.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR r.halfYear = :halfYear)
            GROUP BY r.city
            """)
    List<ReferenceeSummaryDTO> getReferenceeSummaryByCity (
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear );

    //Group By branch
    @Query("""
            SELECT new com.mandovi.DTO.ReferenceeSummaryDTO(
            MAX(r.city),
            r.branch,
            SUM(r.referencee),
            SUM(r.enquiry),
            SUM(r.booking),
            SUM(r.invoice),
            SUM(r.booking) * 100.00 / NULLIF(SUM(r.enquiry), 0),
            SUM(r.invoice) * 100.00 / NULLIF(SUM(r.enquiry), 0),
            SUM(r.invoice) * 100.00 / NULLIF(SUM(r.booking), 0)
            )
            FROM Referencee r
            WHERE (:month IS NULL OR r.month = :month)
            AND (:qtrWise IS NULL OR r.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR r.halfYear = :halfYear)
            GROUP BY r.branch
            """)
    List<ReferenceeSummaryDTO> getReferenceeSummaryByBranch (
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear );

    //Group By city and branch
    @Query("""
            SELECT new com.mandovi.DTO.ReferenceeSummaryDTO(
            r.city,
            r.branch,
            SUM(r.referencee),
            SUM(r.enquiry),
            SUM(r.booking),
            SUM(r.invoice),
            SUM(r.booking) * 100.00 / NULLIF(SUM(r.enquiry), 0),
            SUM(r.invoice) * 100.00 / NULLIF(SUM(r.enquiry), 0),
            SUM(r.invoice) * 100.00 / NULLIF(SUM(r.booking), 0)
            )
            FROM Referencee r
            WHERE (:month IS NULL OR r.month = :month)
            AND (:qtrWise IS NULL OR r.qtrWise = :qtrWise)
            AND (:halfYear IS NULL OR r.halfYear = :halfYear)
            GROUP BY r.city, r.branch
            """)
    List<ReferenceeSummaryDTO> getReferenceeSummaryByCityAndBranch (
            @Param("month") String month,
            @Param("qtrWise") String qtrWise,
            @Param("halfYear") String halfYear );
}
