package com.mandovi.Repository;

import com.mandovi.DTO.PMSPartsSummaryDTO;
import com.mandovi.Entity.PMSParts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PMSPartsRepository extends JpaRepository<PMSParts, Integer> {

    @Transactional
    @Query("""
    SELECT p FROM PMSParts p
    WHERE (:months IS NULL OR p.month IN (:months))
    AND (:years IS NULL OR p.year IN (:years))
    """)
    public List<PMSParts> getPMSPartsByMonthYear(@Param("months") List<String> months, @Param("years") List<String> years);

    //Group  By city
    @Query("""
        SELECT new com.mandovi.DTO.PMSPartsSummaryDTO(
        p.city,
        null,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'Air filter' THEN p.required ELSE 0 END) = 0
             THEN 0
             ELSE SUM(CASE WHEN p.partGroup = 'Air filter' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'Air filter' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'Belt water pump' THEN p.required ELSE 0 END) = 0 
             THEN 0 
             ELSE SUM(CASE WHEN p.partGroup = 'Belt water pump' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'Belt water pump' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'Brake fluid' THEN p.required ELSE 0 END) = 0 
             THEN 0 
             ELSE SUM(CASE WHEN p.partGroup = 'Brake fluid' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'Brake fluid' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'Coolant' THEN p.required ELSE 0 END) = 0 
             THEN 0 
             ELSE SUM(CASE WHEN p.partGroup = 'Coolant' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'Coolant' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'Fuel Filter' THEN p.required ELSE 0 END) = 0 
             THEN 0 
             ELSE SUM(CASE WHEN p.partGroup = 'Fuel Filter' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'Fuel Filter' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'Oil filter' THEN p.required ELSE 0 END) = 0 
             THEN 0 
             ELSE SUM(CASE WHEN p.partGroup = 'Oil filter' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'Oil filter' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'Spark plug' THEN p.required ELSE 0 END) = 0 
             THEN 0 
             ELSE SUM(CASE WHEN p.partGroup = 'Spark plug' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'Spark plug' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup IN ('Air filter','Belt water pump','Brake fluid','Coolant','Fuel Filter','Oil filter','Spark plug') THEN p.required ELSE 0 END) = 0
             THEN 0
             ELSE SUM(CASE WHEN p.partGroup IN ('Air filter','Belt water pump','Brake fluid','Coolant','Fuel Filter','Oil filter','Spark plug') THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup IN ('Air filter','Belt water pump','Brake fluid','Coolant','Fuel Filter','Oil filter','Spark plug') THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'DRAIN PLUG GASKET' THEN p.required ELSE 0 END) = 0
             THEN 0
             ELSE SUM(CASE WHEN p.partGroup = 'DRAIN PLUG GASKET' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'DRAIN PLUG GASKET' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'ISG BELT GENERATOR' THEN p.required ELSE 0 END) = 0
             THEN 0
             ELSE SUM(CASE WHEN p.partGroup = 'ISG BELT GENERATOR' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'ISG BELT GENERATOR' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'CNG FILTER' THEN p.required ELSE 0 END) = 0
             THEN 0
             ELSE SUM(CASE WHEN p.partGroup = 'CNG FILTER' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'CNG FILTER' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup IN ('DRAIN PLUG GASKET','ISG BELT GENERATOR','CNG FILTER') THEN p.required ELSE 0 END) = 0
             THEN 0
             ELSE SUM(CASE WHEN p.partGroup IN ('DRAIN PLUG GASKET','ISG BELT GENERATOR','CNG FILTER') THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup IN ('DRAIN PLUG GASKET','ISG BELT GENERATOR','CNG FILTER') THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(p.required) = 0
             THEN 0
             ELSE SUM(p.changed) * 100.0 / SUM(p.required)
        END
        )
        FROM PMSParts p
        WHERE (:months IS NULL OR p.month IN (:months))
         AND (:qtrWise IS NULL OR p.qtrWise IN (:qtrWise))
         AND (:halfYear IS NULL OR p.halfYear IN (:halfYear))
        GROUP BY p.city
        """)
    List<PMSPartsSummaryDTO> getPMSPartsSummaryByCity(
            @Param("months") List<String> months,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear
    );

    //Group  By branch
    @Query("""
        SELECT new com.mandovi.DTO.PMSPartsSummaryDTO(
        p.city,
        p.branch,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'Air filter' THEN p.required ELSE 0 END) = 0
             THEN 0
             ELSE SUM(CASE WHEN p.partGroup = 'Air filter' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'Air filter' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'Belt water pump' THEN p.required ELSE 0 END) = 0 
             THEN 0 
             ELSE SUM(CASE WHEN p.partGroup = 'Belt water pump' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'Belt water pump' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'Brake fluid' THEN p.required ELSE 0 END) = 0 
             THEN 0 
             ELSE SUM(CASE WHEN p.partGroup = 'Brake fluid' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'Brake fluid' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'Coolant' THEN p.required ELSE 0 END) = 0 
             THEN 0 
             ELSE SUM(CASE WHEN p.partGroup = 'Coolant' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'Coolant' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'Fuel Filter' THEN p.required ELSE 0 END) = 0 
             THEN 0 
             ELSE SUM(CASE WHEN p.partGroup = 'Fuel Filter' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'Fuel Filter' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'Oil filter' THEN p.required ELSE 0 END) = 0 
             THEN 0 
             ELSE SUM(CASE WHEN p.partGroup = 'Oil filter' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'Oil filter' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'Spark plug' THEN p.required ELSE 0 END) = 0 
             THEN 0 
             ELSE SUM(CASE WHEN p.partGroup = 'Spark plug' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'Spark plug' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup IN ('Air filter','Belt water pump','Brake fluid','Coolant','Fuel Filter','Oil filter','Spark plug') THEN p.required ELSE 0 END) = 0
             THEN 0
             ELSE SUM(CASE WHEN p.partGroup IN ('Air filter','Belt water pump','Brake fluid','Coolant','Fuel Filter','Oil filter','Spark plug') THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup IN ('Air filter','Belt water pump','Brake fluid','Coolant','Fuel Filter','Oil filter','Spark plug') THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'DRAIN PLUG GASKET' THEN p.required ELSE 0 END) = 0
             THEN 0
             ELSE SUM(CASE WHEN p.partGroup = 'DRAIN PLUG GASKET' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'DRAIN PLUG GASKET' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'ISG BELT GENERATOR' THEN p.required ELSE 0 END) = 0
             THEN 0
             ELSE SUM(CASE WHEN p.partGroup = 'ISG BELT GENERATOR' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'ISG BELT GENERATOR' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup = 'CNG FILTER' THEN p.required ELSE 0 END) = 0
             THEN 0
             ELSE SUM(CASE WHEN p.partGroup = 'CNG FILTER' THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup = 'CNG FILTER' THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(CASE WHEN p.partGroup IN ('DRAIN PLUG GASKET','ISG BELT GENERATOR','CNG FILTER') THEN p.required ELSE 0 END) = 0
             THEN 0
             ELSE SUM(CASE WHEN p.partGroup IN ('DRAIN PLUG GASKET','ISG BELT GENERATOR','CNG FILTER') THEN p.changed ELSE 0 END) * 100.0 /
                  SUM(CASE WHEN p.partGroup IN ('DRAIN PLUG GASKET','ISG BELT GENERATOR','CNG FILTER') THEN p.required ELSE 0 END)
        END,
        CASE WHEN SUM(p.required) = 0
             THEN 0
             ELSE SUM(p.changed) * 100.0 / SUM(p.required)
        END
        )
        FROM PMSParts p
        WHERE (:months IS NULL OR p.month IN (:months))
         AND (:cities IS NULL OR p.city IN (:cities))
         AND (:qtrWise IS NULL OR p.qtrWise IN (:qtrWise))
         AND (:halfYear IS NULL OR p.halfYear IN (:halfYear))
        GROUP BY p.city, p.branch
        """)
    List<PMSPartsSummaryDTO> getPMSPartsSummaryBranchWise(
            @Param("months") List<String> months,
            @Param("cities") List<String> cities,
            @Param("qtrWise") List<String> qtrWise,
            @Param("halfYear") List<String> halfYear );

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE mandovi.pms_parts;", nativeQuery = true)
    void deletePMSPartsALl ();
}
