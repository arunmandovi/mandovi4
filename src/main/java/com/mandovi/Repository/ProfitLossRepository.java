package com.mandovi.Repository;

import com.mandovi.DTO.ProfitLossSummaryDTO;
import com.mandovi.Entity.ProfitLoss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfitLossRepository extends JpaRepository<ProfitLoss, Integer> {

    ProfitLoss findByCityAndBranch(String city, String branch);

    @Query(name = "ProfitLoss.getProfitLossSummary", nativeQuery = true)
    List<ProfitLossSummaryDTO> getProfitLossSummary ();

    @Query(name = "ProfitLoss.getProfitLossSummaryByCityBranch", nativeQuery = true)
    List<ProfitLossSummaryDTO> getProfitLossSummaryByCityBranch(@Param("cities") List<String> cities);



}
