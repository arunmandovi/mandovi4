package com.mandovi.Entity;

import com.mandovi.DTO.ProfitLossSummaryDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "profit_loss")
@NamedNativeQuery(
        name = "ProfitLoss.getProfitLossSummary",
        query = """
        SELECT
            t.city AS city,
            t.`Apr-24` AS Apr_24,
            t.`May-24` AS May_24,
            t.`Jun-24` AS Jun_24,
            t.`Jul-24` AS Jul_24,
            t.`Total_24` AS Total_24,
            t.`Apr-25` AS Apr_25,
            t.`May-25` AS May_25,
            t.`Jun-25` AS Jun_25,
            t.`Jul-25` AS Jul_25,
            t.`Aug-25` AS Aug_25,
            t.`2025-26` AS FY_2025_26,
            t.load_Apr AS load_Apr,
            t.load_May AS load_May,
            t.load_Jun AS load_Jun,
            t.load_Jul AS load_Jul,
            t.load_Aug AS load_Aug,
            t.load_Total AS load_Total,
            ROUND((t.`Apr-25` / NULLIF(t.load_Apr, 0)) * 100000, 3) AS Apr25_per_100k,
            ROUND((t.`May-25` / NULLIF(t.load_May, 0)) * 100000, 3) AS May25_per_100k,
            ROUND((t.`Jun-25` / NULLIF(t.load_Jun, 0)) * 100000, 3) AS Jun25_per_100k,
            ROUND((t.`Jul-25` / NULLIF(t.load_Jul, 0)) * 100000, 3) AS Jul25_per_100k,
            ROUND((t.`Aug-25` / NULLIF(t.load_Aug, 0)) * 100000, 3) AS Aug25_per_100k,
            ROUND((t.`Total_25` / NULLIF(t.load_Total, 0)) * 100000, 3) AS Total25_per_100k
        FROM (
            SELECT
                pl.city,
                null,
                SUM(pl.`Apr-24`) AS `Apr-24`,
                SUM(pl.`May-24`) AS `May-24`,
                SUM(pl.`Jun-24`) AS `Jun-24`,
                SUM(pl.`Jul-24`) AS `Jul-24`,
                (SUM(pl.`Apr-24`) + SUM(pl.`May-24`) + SUM(pl.`Jun-24`) + SUM(pl.`Jul-24`)) AS `Total_24`,
                SUM(pl.`Apr-25`) AS `Apr-25`,
                SUM(pl.`May-25`) AS `May-25`,
                SUM(pl.`Jun-25`) AS `Jun-25`,
                SUM(pl.`Jul-25`) AS `Jul-25`,
                SUM(pl.`Aug-25`) AS `Aug-25`,
                (SUM(pl.`Apr-25`) + SUM(pl.`May-25`) + SUM(pl.`Jun-25`) + SUM(pl.`Jul-25`) + SUM(pl.`Aug-25`)) AS `Total_25`,
                SUM(pl.`2025-26`) AS `2025-26`,
                COALESCE((SELECT SUM(l.service_load) FROM mandovi.loadd l WHERE l.city = pl.city AND l.month = 'Apr' AND l.year = '2025' AND l.load_type NOT IN ('NO')), 0) AS load_Apr,
                COALESCE((SELECT SUM(l.service_load) FROM mandovi.loadd l WHERE l.city = pl.city AND l.month = 'May' AND l.year = '2025' AND l.load_type NOT IN ('NO')), 0) AS load_May,
                COALESCE((SELECT SUM(l.service_load) FROM mandovi.loadd l WHERE l.city = pl.city AND l.month = 'Jun' AND l.year = '2025' AND l.load_type NOT IN ('NO')), 0) AS load_Jun,
                COALESCE((SELECT SUM(l.service_load) FROM mandovi.loadd l WHERE l.city = pl.city AND l.month = 'Jul' AND l.year = '2025' AND l.load_type NOT IN ('NO')), 0) AS load_Jul,
                COALESCE((SELECT SUM(l.service_load) FROM mandovi.loadd l WHERE l.city = pl.city AND l.month = 'Aug' AND l.year = '2025' AND l.load_type NOT IN ('NO')), 0) AS load_Aug,
                COALESCE((SELECT SUM(l.service_load) FROM mandovi.loadd l WHERE l.city = pl.city AND l.month IN ('Apr', 'May', 'Jun', 'Jul', 'Aug') AND l.year = '2025' AND l.load_type NOT IN ('NO')), 0) AS load_Total
            FROM mandovi.profit_loss pl
            WHERE pl.city IN ('Bangalore', 'Mysore', 'Mangalore')
            GROUP BY pl.city
        ) t
    """,
        resultSetMapping = "ProfitLossSummaryMapping"
)
@SqlResultSetMapping(
        name = "ProfitLossSummaryMapping",
        classes = @ConstructorResult(
                targetClass = ProfitLossSummaryDTO.class,
                columns = {
                        @ColumnResult(name = "city", type = String.class),
                        @ColumnResult(name = "Apr_24", type = Double.class),
                        @ColumnResult(name = "May_24", type = Double.class),
                        @ColumnResult(name = "Jun_24", type = Double.class),
                        @ColumnResult(name = "Jul_24", type = Double.class),
                        @ColumnResult(name = "Total_24", type = Double.class),
                        @ColumnResult(name = "Apr_25", type = Double.class),
                        @ColumnResult(name = "May_25", type = Double.class),
                        @ColumnResult(name = "Jun_25", type = Double.class),
                        @ColumnResult(name = "Jul_25", type = Double.class),
                        @ColumnResult(name = "Aug_25", type = Double.class),
                        @ColumnResult(name = "FY_2025_26", type = Double.class),
                        @ColumnResult(name = "load_Apr", type = Double.class),
                        @ColumnResult(name = "load_May", type = Double.class),
                        @ColumnResult(name = "load_Jun", type = Double.class),
                        @ColumnResult(name = "load_Jul", type = Double.class),
                        @ColumnResult(name = "load_Aug", type = Double.class),
                        @ColumnResult(name = "load_Total", type = Double.class),
                        @ColumnResult(name = "Apr25_per_100k", type = Double.class),
                        @ColumnResult(name = "May25_per_100k", type = Double.class),
                        @ColumnResult(name = "Jun25_per_100k", type = Double.class),
                        @ColumnResult(name = "Jul25_per_100k", type = Double.class),
                        @ColumnResult(name = "Aug25_per_100k", type = Double.class),
                        @ColumnResult(name = "Total25_per_100k", type = Double.class)
                }
        )
)
@NamedNativeQuery(
        name = "ProfitLoss.getProfitLossSummaryByCityBranch",
        query = """
        SELECT
            t.city AS city,
            t.branch AS branch,
            t.`Apr-24` AS Apr_24,
            t.`May-24` AS May_24,
            t.`Jun-24` AS Jun_24,
            t.`Jul-24` AS Jul_24,
            t.`Total_24` AS Total_24,
            t.`Apr-25` AS Apr_25,
            t.`May-25` AS May_25,
            t.`Jun-25` AS Jun_25,
            t.`Jul-25` AS Jul_25,
            t.`Aug-25` AS Aug_25,
            t.`2025-26` AS FY_2025_26,
            t.load_Apr AS load_Apr,
            t.load_May AS load_May,
            t.load_Jun AS load_Jun,
            t.load_Jul AS load_Jul,
            t.load_Aug AS load_Aug,
            t.load_Total AS load_Total,
            ROUND((t.`Apr-25` / NULLIF(t.load_Apr, 0)) * 100000, 3) AS Apr25_per_100k,
            ROUND((t.`May-25` / NULLIF(t.load_May, 0)) * 100000, 3) AS May25_per_100k,
            ROUND((t.`Jun-25` / NULLIF(t.load_Jun, 0)) * 100000, 3) AS Jun25_per_100k,
            ROUND((t.`Jul-25` / NULLIF(t.load_Jul, 0)) * 100000, 3) AS Jul25_per_100k,
            ROUND((t.`Aug-25` / NULLIF(t.load_Aug, 0)) * 100000, 3) AS Aug25_per_100k,
            ROUND((t.`Total_25` / NULLIF(t.load_Total, 0)) * 100000, 3) AS Total25_per_100k
        FROM (
            SELECT
                pl.city,
                pl.branch,
                SUM(pl.`Apr-24`) AS `Apr-24`,
                SUM(pl.`May-24`) AS `May-24`,
                SUM(pl.`Jun-24`) AS `Jun-24`,
                SUM(pl.`Jul-24`) AS `Jul-24`,
                (SUM(pl.`Apr-24`) + SUM(pl.`May-24`) + SUM(pl.`Jun-24`) + SUM(pl.`Jul-24`)) AS `Total_24`,
                SUM(pl.`Apr-25`) AS `Apr-25`,
                SUM(pl.`May-25`) AS `May-25`,
                SUM(pl.`Jun-25`) AS `Jun-25`,
                SUM(pl.`Jul-25`) AS `Jul-25`,
                SUM(pl.`Aug-25`) AS `Aug-25`,
                (SUM(pl.`Apr-25`) + SUM(pl.`May-25`) + SUM(pl.`Jun-25`) + SUM(pl.`Jul-25`) + SUM(pl.`Aug-25`)) AS `Total_25`,
                SUM(pl.`2025-26`) AS `2025-26`,
                COALESCE((SELECT SUM(l.service_load) FROM mandovi.loadd l
                          WHERE l.branch = pl.branch AND l.month = 'Apr' AND l.year = '2025'
                          AND l.load_type NOT IN ('NO')), 0) AS load_Apr,
                COALESCE((SELECT SUM(l.service_load) FROM mandovi.loadd l
                          WHERE l.branch = pl.branch AND l.month = 'May' AND l.year = '2025'
                          AND l.load_type NOT IN ('NO')), 0) AS load_May,
                COALESCE((SELECT SUM(l.service_load) FROM mandovi.loadd l
                          WHERE l.branch = pl.branch AND l.month = 'Jun' AND l.year = '2025'
                          AND l.load_type NOT IN ('NO')), 0) AS load_Jun,
                COALESCE((SELECT SUM(l.service_load) FROM mandovi.loadd l
                          WHERE l.branch = pl.branch AND l.month = 'Jul' AND l.year = '2025'
                          AND l.load_type NOT IN ('NO')), 0) AS load_Jul,
                COALESCE((SELECT SUM(l.service_load) FROM mandovi.loadd l
                          WHERE l.branch = pl.branch AND l.month = 'Aug' AND l.year = '2025'
                          AND l.load_type NOT IN ('NO')), 0) AS load_Aug,
                COALESCE((SELECT SUM(l.service_load) FROM mandovi.loadd l
                          WHERE l.branch = pl.branch AND l.month IN ('Apr','May','Jun','Jul','Aug')
                          AND l.year = '2025' AND l.load_type NOT IN ('NO')), 0) AS load_Total
            FROM mandovi.profit_loss pl
            WHERE (:cities IS NULL OR pl.city IN (:cities))
            GROUP BY pl.city, pl.branch
        ) t
    """,
        resultSetMapping = "ProfitLossSummaryByCityBranchMapping"
)

@SqlResultSetMapping(
        name = "ProfitLossSummaryByCityBranchMapping",
        classes = @ConstructorResult(
                targetClass = com.mandovi.DTO.ProfitLossSummaryDTO.class,
                columns = {
                        @ColumnResult(name = "city", type = String.class),
                        @ColumnResult(name = "branch", type = String.class),
                        @ColumnResult(name = "Apr_24", type = Double.class),
                        @ColumnResult(name = "May_24", type = Double.class),
                        @ColumnResult(name = "Jun_24", type = Double.class),
                        @ColumnResult(name = "Jul_24", type = Double.class),
                        @ColumnResult(name = "Total_24", type = Double.class),
                        @ColumnResult(name = "Apr_25", type = Double.class),
                        @ColumnResult(name = "May_25", type = Double.class),
                        @ColumnResult(name = "Jun_25", type = Double.class),
                        @ColumnResult(name = "Jul_25", type = Double.class),
                        @ColumnResult(name = "Aug_25", type = Double.class),
                        @ColumnResult(name = "FY_2025_26", type = Double.class),
                        @ColumnResult(name = "load_Apr", type = Double.class),
                        @ColumnResult(name = "load_May", type = Double.class),
                        @ColumnResult(name = "load_Jun", type = Double.class),
                        @ColumnResult(name = "load_Jul", type = Double.class),
                        @ColumnResult(name = "load_Aug", type = Double.class),
                        @ColumnResult(name = "load_Total", type = Double.class),
                        @ColumnResult(name = "Apr25_per_100k", type = Double.class),
                        @ColumnResult(name = "May25_per_100k", type = Double.class),
                        @ColumnResult(name = "Jun25_per_100k", type = Double.class),
                        @ColumnResult(name = "Jul25_per_100k", type = Double.class),
                        @ColumnResult(name = "Aug25_per_100k", type = Double.class),
                        @ColumnResult(name = "Total25_per_100k", type = Double.class)
                }
        )
)

public class ProfitLoss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profit_lossSINo")
    private Integer profit_lossSIno;

    @Column(name = "city")
    private String city;

    @Column(name = "branch")
    private String branch;

    public ProfitLoss() {}

    public ProfitLoss(Integer profit_lossSIno, String city, String branch) {
        this.profit_lossSIno = profit_lossSIno;
        this.city = city;
        this.branch = branch;
    }

    public Integer getProfit_lossSIno() {
        return profit_lossSIno;
    }

    public void setProfit_lossSIno(Integer profit_lossSIno) {
        this.profit_lossSIno = profit_lossSIno;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "ProfitLoss{" +
                "profit_lossSIno=" + profit_lossSIno +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }
}
