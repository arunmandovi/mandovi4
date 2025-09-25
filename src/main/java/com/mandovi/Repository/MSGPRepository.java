package com.mandovi.Repository;

import com.mandovi.Entity.MSGP;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MSGPRepository extends JpaRepository<MSGP, Integer> {

    @Transactional
    @Query("SELECT m FROM MSGP m WHERE m.month = :month AND m.year = :year")
    public List<MSGP> getMSGPByMonthYear(@Param("month") String month,@Param("year") String year);
}
