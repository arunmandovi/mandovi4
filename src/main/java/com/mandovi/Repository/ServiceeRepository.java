package com.mandovi.Repository;

import com.mandovi.DTO.ServiceeSummaryDTO;
import com.mandovi.Entity.Servicee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceeRepository extends JpaRepository<Servicee, Integer> {
    @Query("""
            SELECT s FROM Servicee s
            WHERE (:months IS NULL OR s.month IN (:months)) AND (:years IS NULL OR s.year IN (:years))
            """)
    public List<Servicee> getServicee (@Param("months") List<String> months, @Param("years") List<String> years);

    @Transactional
    @Modifying
    @Query("DELETE FROM Servicee s WHERE s.month = :month AND s.year = :year")
    void deleteByMonthYear (@Param("month") String month, @Param("year") String year );

    @Query("""
           SELECT new com.mandovi.DTO.ServiceeSummaryDTO(
               s.city,
               s.branch,
               SUM(s.serviceLoadd)
           )
           FROM Servicee s
           WHERE s.city = 'Mangalore'
           AND (:months IS NULL OR s.month IN (:months))
           AND (:years IS NULL OR s.year IN (:years))
           AND (:branches IS NULL OR s.branch IN (:branches))
           AND (:channels IS NULL OR s.channel IN (:channels))
           AND (:serviceCodes IS NULL OR s.serviceCode IN (:serviceCodes))
           GROUP BY s.city,s.branch
           """)
    public List<ServiceeSummaryDTO> getServiceSummaryBranchWise (
            @Param("months") List<String> months,
            @Param("years") List<String> years,
            @Param("branches") List<String> branches,
            @Param("channels") List<String> channels,
            @Param("serviceCodes") List<String> serviceCodes );
}
