package com.mandovi.Repository;

import com.mandovi.Entity.ProfitLoss;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfitLossRepository extends JpaRepository<ProfitLoss, Integer> {

    ProfitLoss findByCityAndBranch(String city, String branch);

}
