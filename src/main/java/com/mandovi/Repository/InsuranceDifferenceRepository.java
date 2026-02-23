package com.mandovi.Repository;

import com.mandovi.Entity.InsuranceDifference;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface InsuranceDifferenceRepository extends JpaRepository<InsuranceDifference, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.insurance_difference;", nativeQuery = true)
    void deleteInsuranceDifferenceAll ();

    @Modifying
    @Transactional
    @Query(value = """
INSERT INTO insurance_difference
(
    segment,
    ledger_name,
    party_name,
    insurance_party,
    insurance_difference_date,
    bill_no,
    bill_amt,
    paid_amt,
    balance_amt,
    insurance_amt,
    difference_amt,
    due_since,
    upto_seven,
    eight_to_thirty,
    thirtyone_to_ninty,
    more_than_ninty,
    sales_man
)
SELECT
    o.segment,
    o.ledger_group_name,
    o.party_name,

    CASE
        WHEN UPPER(o.party_name) LIKE '%GO DIGIT%' THEN 'GO DIGIT'
        WHEN UPPER(o.party_name) LIKE '%INDUSIND%' THEN 'INDUSIND'
        WHEN UPPER(o.party_name) LIKE '%TATA AIG%' THEN 'TATA AIG'
        WHEN UPPER(o.party_name) LIKE '%THE NEW INDIA%' THEN 'NEW INDIA'
        WHEN UPPER(o.party_name) LIKE '%THE ORIENTAL%' THEN 'ORIENTAL'
        WHEN UPPER(o.party_name) LIKE '%GENERALI CENTRAL%' THEN 'GENERALI CENTRAL'
        ELSE TRIM(SUBSTRING_INDEX(COALESCE(o.party_name,''), ' ', 1))
    END,

    o.outstanding_date,
    o.bill_no,
    o.bill_amt,
    o.paid_amt,
    o.balance_amt,

    CASE
        WHEN (COALESCE(o.bill_amt,0) / 2) >= COALESCE(o.paid_amt,0)
        THEN (COALESCE(o.bill_amt,0) - COALESCE(o.paid_amt,0))
        ELSE 0.0
    END,

    CASE
        WHEN (COALESCE(o.bill_amt,0) / 2) < COALESCE(o.paid_amt,0)
        THEN (COALESCE(o.bill_amt,0) - COALESCE(o.paid_amt,0))
        ELSE 0.0
    END,

    o.due_since,
    o.upto_seven,
    o.eight_to_thirty,
    o.thirtyone_to_ninty,
    o.more_than_ninty,
    o.sales_man

FROM outstanding o
WHERE UPPER(o.bill_no) LIKE '%BI%'
AND NOT EXISTS (
    SELECT 1
    FROM insurance_difference id
    WHERE UPPER(TRIM(id.bill_no)) = UPPER(TRIM(o.bill_no))
)
""", nativeQuery = true)
    void insertInsuranceDifferenceFromOutstanding();
}
