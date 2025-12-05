package com.mandovi.Repository;

import com.mandovi.DTO.ReferenceeSummaryDTO;
import com.mandovi.DTO.ReferenceeTableDTO;
import com.mandovi.Entity.Referencee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReferenceeRepository extends JpaRepository<Referencee, Integer> {

    @Transactional
    @Query("""
    SELECT r FROM Referencee r
    WHERE (:months IS NULL OR r.month IN (:months))
    AND (:years IS NULL OR r.year IN (:years))
    """)
    public List<Referencee> getReferenceeByMonthYear(@Param("months") List<String> months,@Param("years") List<String> years);

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
            WHERE (:months IS NULL OR r.month IN (:months))
            AND (:channels IS NULL OR r.channel IN (:channels))
            AND (:qtrWise IS NULL OR r.qtrWise IN (:qtrWise))
            AND (:halfYear IS NULL OR r.halfYear IN (:halfYear))
            GROUP BY r.city
            """)
    List<ReferenceeSummaryDTO> getReferenceeSummaryByCity (
            @Param("months") List<String> months,
            @Param("channels") List<String> channels,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear );

    //Group By branch
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
            WHERE (:months IS NULL OR r.month IN (:months))
             AND (:cities IS NULL OR r.city IN (:cities))
             AND (:channels IS NULL OR r.channel IN (:channels))
             AND (:qtrWise IS NULL OR r.qtrWise IN (:qtrWise))
             AND (:halfYear IS NULL OR r.halfYear IN (:halfYear))
            GROUP BY r.city, r.branch
            """)
    List<ReferenceeSummaryDTO> getReferenceeSummaryBranchWise (
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("channels") List<String> channels,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear );

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.referencee;", nativeQuery = true)
    void deleteReferenceeAll ();

    //Table Summary Group By groupDesignation
    @Query("""
        SELECT new com.mandovi.DTO.ReferenceeTableDTO(
            r.groupDesignation,
            SUM(r.referencee),
            SUM(r.enquiry),
            SUM(r.booking),
            SUM(r.enquiry)
        )
        FROM Referencee r
        WHERE (:months IS NULL OR r.month IN (:months))
          AND (:cities IS NULL OR r.city IN (:cities))
        GROUP BY r.groupDesignation
        """)
    List<ReferenceeTableDTO> getReferenceeTableCityWise(
            @Param("months") List<String> months,
            @Param("cities") List<String> cities
    );
}
