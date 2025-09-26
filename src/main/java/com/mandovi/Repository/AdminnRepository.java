package com.mandovi.Repository;

import com.mandovi.Entity.Adminn;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminnRepository extends JpaRepository<Adminn, Integer> {

    @Transactional
    @Query("SELECT a FROM Adminn a WHERE a.adminnId = :adminnId")
    Optional<Adminn> findByAdminnId (@Param("adminnId") String adminnId);

}
