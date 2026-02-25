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
            "AND bill_no not LIKE '%BR%' AND bill_no not LIKE '%BC%' AND bill_no not LIKE '%RS%' AND bill_no not LIKE '%CSI%';", nativeQuery = true)
    public List<Outstanding> getOthersOutstandingSAWise();

    //TotalOutstanding Queries
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, null, null,null, SUM(o.billAmt), SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE (:segments is NULL OR o.segment IN (:segments))
            GROUP BY o.segment
            """)
    List<TotalOutstandingDTO> getTotalOutstandingBranchWise (
            @Param("segments") List<String> segments );
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, o.salesMan, null,null, SUM(o.billAmt), SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
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
        o.segment, o.salesMan, o.partyName,o.billNo, SUM(o.billAmt), SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
        SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
        FROM Outstanding o
        WHERE (:segments is NULL OR o.segment IN (:segments))
         AND (:salesMans IS NULL OR o.salesMan IN (:salesMans))
         AND (:party IS NULL OR LOWER(o.partyName) LIKE LOWER(CONCAT('%', :party, '%')))
        GROUP BY o.segment, o.salesMan, o.partyName,o.billNo
        """)
    List<TotalOutstandingDTO> getTotalOutstandingPartyNameWise(
            @Param("segments") List<String> segments,
            @Param("salesMans") List<String> salesMans,
            @Param("party") String party);

    //CashOutstanding Queries
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, null, null,null, SUM(o.billAmt), SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
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
            o.segment, o.salesMan, null,null, SUM(o.billAmt), SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
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
            o.segment, o.salesMan, o.partyName,o.billNo, SUM(o.billAmt), SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo LIKE '%BC%'
             AND (:segments is NULL OR o.segment IN (:segments))
             AND (:salesMans IS NULL OR o.salesMan IN (:salesMans))
             AND (:party IS NULL OR LOWER(o.partyName) LIKE LOWER(CONCAT('%', :party, '%')))
            GROUP BY o.segment,o.salesMan, o.partyName,o.billNo
            """)
    List<TotalOutstandingDTO> getCashOutstandingPartyWise(
            @Param("segments") List<String> segments,
            @Param("salesMans") List<String> salesMans,
            @Param("party") String party );

    //InvoiceOutstanding Queries
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, null, null,null, SUM(o.billAmt), SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
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
            o.segment, o.salesMan, null,null,SUM(o.billAmt), SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
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
            o.segment, o.salesMan, o.partyName,o.billNo,SUM(o.billAmt), SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo LIKE '%BR%'
             AND (:segments is NULL OR o.segment IN (:segments))
             AND (:salesMans IS NULL OR o.salesMan IN (:salesMans))
             AND (:party IS NULL OR LOWER(o.partyName) LIKE LOWER(CONCAT('%', :party, '%')))
            GROUP BY o.segment,o.salesMan, o.partyName,o.billNo
            """)
    List<TotalOutstandingDTO> getInvoiceOutstandingPartyWise(
            @Param("segments") List<String> segments,
            @Param("salesMans") List<String> salesMans,
            @Param("party") String party );

    //OthersOutstanding Queries
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, null, null,null,SUM(o.billAmt), SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo NOT LIKE '%BC/%'
             AND o.billNo NOT LIKE '%BR/%'
             AND o.billNo NOT LIKE '%BI/%'
             AND o.billNo NOT LIKE '%RS/%'
             AND o.billNo NOT LIKE '%CSI/%'
             AND (:segments is NULL OR o.segment IN (:segments))
            GROUP BY o.segment
            """)
    List<TotalOutstandingDTO> getOthersOutstandingBranchWise (
            @Param("segments") List<String> segments );
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, o.salesMan, null,null,SUM(o.billAmt), SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo NOT LIKE '%BC/%'
             AND o.billNo NOT LIKE '%BR/%'
             AND o.billNo NOT LIKE '%BI/%'
             AND o.billNo NOT LIKE '%RS/%'
             AND o.billNo NOT LIKE '%CSI/%'
             AND (:segments is NULL OR o.segment IN (:segments))
             AND (:salesMans IS NULL OR o.salesMan IN (:salesMans))
            GROUP BY o.segment,o.salesMan
            """)
    List<TotalOutstandingDTO> getOthersOutstandingSAWise(
            @Param("segments") List<String> segments,
            @Param("salesMans") List<String> salesMans );
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, o.salesMan, o.partyName,o.billNo,SUM(o.billAmt), SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo NOT LIKE '%BC/%'
             AND o.billNo NOT LIKE '%BR/%'
             AND o.billNo NOT LIKE '%BI/%'
             AND o.billNo NOT LIKE '%RS/%'
             AND o.billNo NOT LIKE '%CSI/%'
             AND (:segments is NULL OR o.segment IN (:segments))
             AND (:salesMans IS NULL OR o.salesMan IN (:salesMans))
             AND (:party IS NULL OR LOWER(o.partyName) LIKE LOWER(CONCAT('%', :party, '%')))
            GROUP BY o.segment,o.salesMan, o.partyName,o.billNo
            """)
    List<TotalOutstandingDTO> getOthersOutstandingPartyWise(
            @Param("segments") List<String> segments,
            @Param("salesMans") List<String> salesMans,
            @Param("party") String party );

    //ID Outstanding Queries
    @Query("""
            SELECT new com.mandovi.DTO.IDOutstandingDTO(
            i.segment,null,null,null,null, SUM(i.billAmt), SUM(i.balanceAmt),SUM(i.insuranceAmt),SUM(i.differenceAmt),
            SUM(i.upToSeven), SUM(i.eightToThirty),SUM(i.thirtyOneToNinty), SUM(i.moreThanNinty))
            FROM InsuranceDifference i
            WHERE (:segments IS NULL OR i.segment IN (:segments))
            GROUP BY i.segment
            """)
    List<IDOutstandingDTO> getIDOutstandingBranchWise (@Param("segments") List<String> segments );

    @Query("""
            SELECT new com.mandovi.DTO.IDOutstandingDTO(
            i.segment,i.insuranceParty,null,null,null, SUM(i.billAmt), SUM(i.balanceAmt),SUM(i.insuranceAmt),SUM(i.differenceAmt),
            SUM(i.upToSeven), SUM(i.eightToThirty),SUM(i.thirtyOneToNinty), SUM(i.moreThanNinty))
            FROM InsuranceDifference i
            WHERE (:segments IS NULL OR i.segment IN (:segments))
             AND (:insuranceParties IS NULL OR i.insuranceParty IN (:insuranceParties))
            GROUP BY i.segment, i.insuranceParty
            """)
    List<IDOutstandingDTO> getIDOutstandingSAWise (
            @Param("segments") List<String> segments,
            @Param("insuranceParties") List<String> insuranceParties);

    @Query("""
            SELECT new com.mandovi.DTO.IDOutstandingDTO(
            i.segment,i.insuranceParty,i.partyName,i.salesMan, i.billNo,
            SUM(i.billAmt), SUM(i.balanceAmt),SUM(i.insuranceAmt),SUM(i.differenceAmt),
            SUM(i.upToSeven), SUM(i.eightToThirty),SUM(i.thirtyOneToNinty), SUM(i.moreThanNinty))
            FROM InsuranceDifference i
            WHERE (:segments IS NULL OR i.segment IN (:segments))
             AND (:insuranceParties IS NULL OR i.insuranceParty IN (:insuranceParties))
             AND (:party IS NULL OR LOWER(i.partyName) LIKE LOWER(CONCAT('%', :party, '%')))
            GROUP BY i.segment, i.insuranceParty,i.partyName,i.salesMan, i.billNo
            """)
    List<IDOutstandingDTO> getIDOutstandingPartyWise (
            @Param("segments") List<String> segments,
            @Param("insuranceParties") List<String> insuranceParties,
            @Param("party") String party );

    //CustomerCollectOutstanding Queries
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, null, null,null,SUM(o.billAmt), SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo NOT LIKE '%RS/%'
             AND o.billNo NOT LIKE '%CSI/%'
             AND (
                   o.billNo NOT LIKE '%BI/%'
                   OR (o.billNo LIKE '%BI/%' AND (o.billAmt / 2) < o.paidAmt)
                 )
             AND (:segments is NULL OR o.segment IN (:segments))
            GROUP BY o.segment
            """)
    List<TotalOutstandingDTO> getCustomerCollectOutstandingBranchWise (
            @Param("segments") List<String> segments );
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, o.salesMan, null,null,SUM(o.billAmt), SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo NOT LIKE '%RS/%'
             AND o.billNo NOT LIKE '%CSI/%'
             AND (
                   o.billNo NOT LIKE '%BI/%'
                   OR (o.billNo LIKE '%BI/%' AND (o.billAmt / 2) < o.paidAmt)
                 )
             AND (:segments is NULL OR o.segment IN (:segments))
             AND (:salesMans IS NULL OR o.salesMan IN (:salesMans))
            GROUP BY o.segment, o.salesMan
            """)
    List<TotalOutstandingDTO> getCustomerCollectOutstandingSAWise (
            @Param("segments") List<String> segments,
            @Param("salesMans") List<String> salesMans );
    @Query("""
            SELECT new com.mandovi.DTO.TotalOutstandingDTO(
            o.segment, o.salesMan, o.partyName,o.billNo,SUM(o.billAmt), SUM(o.balanceAmt), SUM(o.upToSeven), SUM(o.eightToThirty),
            SUM(o.thirtyOneToNinty), SUM(o.moreThanNinty))
            FROM Outstanding o
            WHERE o.billNo NOT LIKE '%RS/%'
             AND o.billNo NOT LIKE '%CSI/%'
             AND (
                   o.billNo NOT LIKE '%BI/%'
                   OR (o.billNo LIKE '%BI/%' AND (o.billAmt / 2) < o.paidAmt)
                 )
             AND (:segments is NULL OR o.segment IN (:segments))
             AND (:salesMans IS NULL OR o.salesMan IN (:salesMans))
             AND (:party IS NULL OR LOWER(o.partyName) LIKE LOWER(CONCAT('%', :party, '%')))
            GROUP BY o.segment, o.salesMan,o.partyName,o.billNo
            """)
    List<TotalOutstandingDTO> getCustomerCollectOutstandingPartyWise (
            @Param("segments") List<String> segments,
            @Param("salesMans") List<String> salesMans,
            @Param("party") String party );

}
