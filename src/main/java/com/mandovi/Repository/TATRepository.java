package com.mandovi.Repository;

import com.mandovi.DTO.TATSummaryDTO;
import com.mandovi.Entity.TAT;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TATRepository extends JpaRepository<TAT,Integer> {

    @Transactional
    @Query("SELECT t FROM TAT t WHERE t.month = :month AND t.year = :year")
    public List<TAT> getTATByMonthYear (@Param("month") String month, @Param("year") String year);

    //Group By city
//    @Query("""
//            SELECT new com.mandovi.DTO.TATSummaryDTO(
//            t.city,
//            null,
//            FUNCTION('SEC_TO_TIME', SUM(FUNCTION('TIME_TO_SEC', CASE WHEN t.linkServiceType = 'FR1' THEN t.averageOfOpenToClose END)) /
//                     COUNT(CASE WHEN t.linkServiceType = 'FR1' THEN 1 END)),
//            FUNCTION('SEC_TO_TIME', SUM(FUNCTION('TIME_TO_SEC', CASE WHEN t.linkServiceType = 'FR2' THEN t.averageOfOpenToClose END)) /
//                     COUNT(CASE WHEN t.linkServiceType = 'FR2' THEN 1 END)),
//            FUNCTION('SEC_TO_TIME', SUM(FUNCTION('TIME_TO_SEC', CASE WHEN t.linkServiceType = 'FR3' THEN t.averageOfOpenToClose END)) /
//                     COUNT(CASE WHEN t.linkServiceType = 'FR3' THEN 1 END)),
//            null
//            )
//            FROM TAT t
//            WHERE (:month IS NULL OR t.month = :month)
//             AND (:qtrWise IS NULL OR t.qtrWise = :qtrWise)
//             AND (:halfYear IS NULL OR t.halfYear = :halfYear)
//            GROUP BY t.city
//            """)
//    List<TATSummaryDTO> getTATSummaryByCity (
//            @Param("month") String month,
//            @Param("qtrWise") String qtrWise,
//            @Param("halfYear") String halfYear );

    //Group By branch
//    @Query("""
//            SELECT new com.mandovi.DTO.TATSummaryDTO(
//            MAX(t.city),
//            t.branch,
//            AVG(CASE WHEN t.linkServiceType = 'FR1' THEN t.averageOfOpenToClose END),
//            AVG(CASE WHEN t.linkServiceType = 'FR2' THEN t.averageOfOpenToClose END),
//            AVG(CASE WHEN t.linkServiceType = 'FR3' THEN t.averageOfOpenToClose END),
//            AVG(CASE WHEN t.linkServiceType = 'PMS' THEN t.averageOfOpenToClose END)
//            )
//            FROM TAT t
//            WHERE (:month IS NULL OR t.month = :month)
//             AND (:qtrWise IS NULL OR t.qtrWise = :qtrWise)
//             AND (:halfYear IS NULL OR t.halfYear = :halfYear)
//            GROUP BY t.branch
//            """)
//    List<TATSummaryDTO> getTATSummaryByBranch (
//            @Param("month") String month,
//            @Param("qtrWise") String qtrWise,
//            @Param("halfYear") String halfYear );
//
//    //Group By city and branch
//    @Query("""
//            SELECT new com.mandovi.DTO.TATSummaryDTO(
//            t.city,
//            t.branch,
//            AVG(CASE WHEN t.linkServiceType = 'FR1' THEN t.averageOfOpenToClose END),
//            AVG(CASE WHEN t.linkServiceType = 'FR2' THEN t.averageOfOpenToClose END),
//            AVG(CASE WHEN t.linkServiceType = 'FR3' THEN t.averageOfOpenToClose END),
//            AVG(CASE WHEN t.linkServiceType = 'PMS' THEN t.averageOfOpenToClose END)
//            )
//            FROM TAT t
//            WHERE (:month IS NULL OR t.month = :month)
//             AND (:qtrWise IS NULL OR t.qtrWise = :qtrWise)
//             AND (:halfYear IS NULL OR t.halfYear = :halfYear)
//            GROUP BY t.city, t.branch
//            """)
//    List<TATSummaryDTO> getTATSummaryByCityAndBranch (
//            @Param("month") String month,
//            @Param("qtrWise") String qtrWise,
//            @Param("halfYear") String halfYear );
}
