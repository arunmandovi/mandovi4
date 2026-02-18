package com.mandovi.Repository;

import com.mandovi.DTO.*;
import com.mandovi.Entity.Outstanding;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OutstandingRepository extends JpaRepository<Outstanding, Integer> {
    void deleteByBalanceAmtLessThanEqual(Double amount);

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.outstanding;", nativeQuery = true)
    void deleteOutstandingAll ();

    @Query(value = "SELECT * FROM mandovi.outstanding WHERE bill_no LIKE '%BC%';", nativeQuery = true)
    public List<Outstanding> getCashOutstandingSAWise();

    @Query(value = "SELECT * FROM mandovi.outstanding WHERE bill_no LIKE '%BR%';", nativeQuery = true)
    public List<Outstanding> getInvoiceOutstanding();

    @Query(value = "SELECT * FROM mandovi.outstanding WHERE bill_no LIKE '%BI%';", nativeQuery = true)
    public List<Outstanding> getInsuranceOutstanding();

    @Query(value = "SELECT * FROM mandovi.outstanding WHERE bill_no not LIKE '%BI%'" +
            "AND bill_no not LIKE '%BR%' AND bill_no not LIKE '%BC%';", nativeQuery = true)
    public List<Outstanding> getOthersOutstandingSAWise();

    //TotalOutstanding Queries
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, null, null, SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE (:segments is NULL OR o.segment IN (:segments))
            GROUP BY o.segment
            """)
    List<TotalOutstandingDTO> getTotalOutstandingBranchWise (
            @Param("segments") List<String> segments );
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, o.salesMan, null, SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE (:segments is NULL OR o.segment IN (:segments))
             AND (:salesMans IS NULL OR o.salesMan IN (:salesMans))
            GROUP BY o.segment,o.salesMan
            """)
    List<TotalOutstandingDTO> getTotalOutstandingSAWise(
            @Param("segments") List<String> segments,
            @Param("salesMans") List<String> salesMans );
    @Query("""
        SELECT new com.mandovi.DTO.TotalOutstandingDTO(
        o.segment, o.salesMan, o.partyName, SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
        SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
        FROM Outstanding o
        WHERE (:segments is NULL OR o.segment IN (:segments))
         AND (:salesMans IS NULL OR o.salesMan IN (:salesMans))
         AND (:party IS NULL OR LOWER(o.partyName) LIKE LOWER(CONCAT('%', :party, '%')))
        GROUP BY o.segment, o.salesMan, o.partyName
        """)
    List<TotalOutstandingDTO> getTotalOutstandingPartyNameWise(
            @Param("segments") List<String> segments,
            @Param("salesMans") List<String> salesMans,
            @Param("party") String party);

    //CashOutstanding Queries
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, null, null, SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo LIKE '%BC%'
             AND (:segments is NULL OR o.segment IN (:segments))
            GROUP BY o.segment
            """)
    List<TotalOutstandingDTO> getCashOutstandingBranchWise(
            @Param("segments") List<String> segments );
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, o.salesMan, null, SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo LIKE '%BC%'
             AND (:segments is NULL OR o.segment IN (:segments))
             AND (:salesMans IS NULL OR o.salesMan IN (:salesMans))
            GROUP BY o.segment,o.salesMan
            """)
    List<TotalOutstandingDTO> getCashOutstandingSAWise(
            @Param("segments") List<String> segments,
            @Param("salesMans") List<String> salesMans );
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, o.salesMan, o.partyName, SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo LIKE '%BC%'
             AND (:segments is NULL OR o.segment IN (:segments))
             AND (:salesMans IS NULL OR o.salesMan IN (:salesMans))
             AND (:party IS NULL OR LOWER(o.partyName) LIKE LOWER(CONCAT('%', :party, '%')))
            GROUP BY o.segment,o.salesMan, o.partyName
            """)
    List<TotalOutstandingDTO> getCashOutstandingPartyWise(
            @Param("segments") List<String> segments,
            @Param("salesMans") List<String> salesMans,
            @Param("party") String party );

    //InvoiceOutstanding Queries
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, null, null, SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo LIKE '%BR%'
             AND (:segments is NULL OR o.segment IN (:segments))
            GROUP BY o.segment
            """)
    List<TotalOutstandingDTO> getInvoiceOutstandingBranchWise (
            @Param("segments") List<String> segments );
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, o.salesMan, null, SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo LIKE '%BR%'
             AND (:segments is NULL OR o.segment IN (:segments))
             AND (:salesMans IS NULL OR o.salesMan IN (:salesMans))
            GROUP BY o.segment,o.salesMan
            """)
    List<TotalOutstandingDTO> getInvoiceOutstandingSAWise (
            @Param("segments") List<String> segments,
            @Param("salesMans") List<String> salesMans );
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, o.salesMan, o.partyName, SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo LIKE '%BR%'
             AND (:segments is NULL OR o.segment IN (:segments))
             AND (:salesMans IS NULL OR o.salesMan IN (:salesMans))
             AND (:party IS NULL OR LOWER(o.partyName) LIKE LOWER(CONCAT('%', :party, '%')))
            GROUP BY o.segment,o.salesMan, o.partyName
            """)
    List<TotalOutstandingDTO> getInvoiceOutstandingPartyWise(
            @Param("segments") List<String> segments,
            @Param("salesMans") List<String> salesMans,
            @Param("party") String party );

    //InsuranceOutstanding Queries
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, null, null, SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo LIKE '%BI%'
             AND (:segments is NULL OR o.segment IN (:segments))
            GROUP BY o.segment
            """)
    List<TotalOutstandingDTO> getInsuranceOutstandingBranchWise (
            @Param("segments") List<String> segments );
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, o.salesMan, null, SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo LIKE '%BI%'
             AND (:segments is NULL OR o.segment IN (:segments))
             AND (:salesMans IS NULL OR o.salesMan IN (:salesMans))
            GROUP BY o.segment,o.salesMan
            """)
    List<TotalOutstandingDTO> getInsuranceOutstandingSAWise (
            @Param("segments") List<String> segments,
            @Param("salesMans") List<String> salesMans );
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, o.salesMan, o.partyName, SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo LIKE '%BI%'
             AND (:segments is NULL OR o.segment IN (:segments))
             AND (:salesMans IS NULL OR o.salesMan IN (:salesMans))
             AND (:party IS NULL OR LOWER(o.partyName) LIKE LOWER(CONCAT('%', :party, '%')))
            GROUP BY o.segment,o.salesMan, o.partyName
            """)
    List<TotalOutstandingDTO> getInsuranceOutstandingPartyWise(
            @Param("segments") List<String> segments,
            @Param("salesMans") List<String> salesMans,
            @Param("party") String party );

    //OthersOutstanding Queries
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, null, null, SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo NOT LIKE '%BC/%'
             AND o.billNo NOT LIKE '%BR/%'
             AND o.billNo NOT LIKE '%BI/%'
             AND (:segments is NULL OR o.segment IN (:segments))
            GROUP BY o.segment
            """)
    List<TotalOutstandingDTO> getOthersOutstandingBranchWise (
            @Param("segments") List<String> segments );
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, o.salesMan, null, SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo NOT LIKE '%BC/%'
             AND o.billNo NOT LIKE '%BR/%'
             AND o.billNo NOT LIKE '%BI/%'
             AND (:segments is NULL OR o.segment IN (:segments))
             AND (:salesMans IS NULL OR o.salesMan IN (:salesMans))
            GROUP BY o.segment,o.salesMan
            """)
    List<TotalOutstandingDTO> getOthersOutstandingSAWise(
            @Param("segments") List<String> segments,
            @Param("salesMans") List<String> salesMans );
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, o.salesMan, o.partyName, SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo NOT LIKE '%BC/%'
             AND o.billNo NOT LIKE '%BR/%'
             AND o.billNo NOT LIKE '%BI/%'
             AND (:segments is NULL OR o.segment IN (:segments))
             AND (:salesMans IS NULL OR o.salesMan IN (:salesMans))
             AND (:party IS NULL OR LOWER(o.partyName) LIKE LOWER(CONCAT('%', :party, '%')))
            GROUP BY o.segment,o.salesMan, o.partyName
            """)
    List<TotalOutstandingDTO> getOthersOutstandingPartyWise(
            @Param("segments") List<String> segments,
            @Param("salesMans") List<String> salesMans,
            @Param("party") String party );


}
