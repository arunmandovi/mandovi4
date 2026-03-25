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
            t.`Sep-25` AS Sep_25,
            t.`Oct-25` AS Oct_25,
            t.`Nov-25` AS Nov_25,
            t.`Dec-25` AS Dec_25,
            t.`Jan-25` AS Jan_25,
            t.`2025-26` AS FY_2025_26,

            t.load_Apr,
            t.load_May,
            t.load_Jun,
            t.load_Jul,
            t.load_Aug,
            t.load_Sep,
            t.load_Oct,
            t.load_Nov,
            t.load_Dec,
            t.load_Jan,
            t.load_Total,

            ROUND((t.`Apr-25` / NULLIF(t.load_Apr,0))*100000,3) AS Apr25_per_100k,
            ROUND((t.`May-25` / NULLIF(t.load_May,0))*100000,3) AS May25_per_100k,
            ROUND((t.`Jun-25` / NULLIF(t.load_Jun,0))*100000,3) AS Jun25_per_100k,
            ROUND((t.`Jul-25` / NULLIF(t.load_Jul,0))*100000,3) AS Jul25_per_100k,
            ROUND((t.`Aug-25` / NULLIF(t.load_Aug,0))*100000,3) AS Aug25_per_100k,
            ROUND((t.`Sep-25` / NULLIF(t.load_Sep,0))*100000,3) AS Sep25_per_100k,
            ROUND((t.`Oct-25` / NULLIF(t.load_Oct,0))*100000,3) AS Oct25_per_100k,
            ROUND((t.`Nov-25` / NULLIF(t.load_Nov,0))*100000,3) AS Nov25_per_100k,
            ROUND((t.`Dec-25` / NULLIF(t.load_Dec,0))*100000,3) AS Dec25_per_100k,
            ROUND((t.`Jan-25` / NULLIF(t.load_Jan,0))*100000,3) AS Jan25_per_100k,
            ROUND((t.`Total_25` / NULLIF(t.load_Total,0))*100000,3) AS Total25_per_100k

        FROM (
            SELECT
                pl.city,

                SUM(pl.apr_24) AS `Apr-24`,
                SUM(pl.may_24) AS `May-24`,
                SUM(pl.jun_24) AS `Jun-24`,
                SUM(pl.jul_24) AS `Jul-24`,
                (SUM(pl.apr_24)+SUM(pl.may_24)+SUM(pl.jun_24)+SUM(pl.jul_24)) AS `Total_24`,

                SUM(pl.apr_25) AS `Apr-25`,
                SUM(pl.may_25) AS `May-25`,
                SUM(pl.jun_25) AS `Jun-25`,
                SUM(pl.jul_25) AS `Jul-25`,
                SUM(pl.aug_25) AS `Aug-25`,
                SUM(pl.sep_25) AS `Sep-25`,
                SUM(pl.oct_25) AS `Oct-25`,
                SUM(pl.nov_25) AS `Nov-25`,
                SUM(pl.dec_25) AS `Dec-25`,
                SUM(pl.jan_25) AS `Jan-25`,

                (SUM(pl.apr_25)+SUM(pl.may_25)+SUM(pl.jun_25)+SUM(pl.jul_25)+
                 SUM(pl.aug_25)+SUM(pl.sep_25)+SUM(pl.oct_25)+SUM(pl.nov_25)+
                 SUM(pl.dec_25)+SUM(pl.jan_25)) AS `Total_25`,

                SUM(pl.`2025_26`) AS `2025-26`,

                MAX(COALESCE(l.load_Apr,0)) AS load_Apr,
                MAX(COALESCE(l.load_May,0)) AS load_May,
                MAX(COALESCE(l.load_Jun,0)) AS load_Jun,
                MAX(COALESCE(l.load_Jul,0)) AS load_Jul,
                MAX(COALESCE(l.load_Aug,0)) AS load_Aug,
                MAX(COALESCE(l.load_Sep,0)) AS load_Sep,
                MAX(COALESCE(l.load_Oct,0)) AS load_Oct,
                MAX(COALESCE(l.load_Nov,0)) AS load_Nov,
                MAX(COALESCE(l.load_Dec,0)) AS load_Dec,
                MAX(COALESCE(l.load_Jan,0)) AS load_Jan,
                MAX(COALESCE(l.load_Total,0)) AS load_Total

            FROM mandovi.profit_loss pl

            LEFT JOIN (
                SELECT
                    city,

                    SUM(CASE WHEN month='Apr' THEN service_load END) AS load_Apr,
                    SUM(CASE WHEN month='May' THEN service_load END) AS load_May,
                    SUM(CASE WHEN month='Jun' THEN service_load END) AS load_Jun,
                    SUM(CASE WHEN month='Jul' THEN service_load END) AS load_Jul,
                    SUM(CASE WHEN month='Aug' THEN service_load END) AS load_Aug,
                    SUM(CASE WHEN month='Sep' THEN service_load END) AS load_Sep,
                    SUM(CASE WHEN month='Oct' THEN service_load END) AS load_Oct,
                    SUM(CASE WHEN month='Nov' THEN service_load END) AS load_Nov,
                    SUM(CASE WHEN month='Dec' THEN service_load END) AS load_Dec,
                    SUM(CASE WHEN month='Jan' THEN service_load END) AS load_Jan,

                    SUM(service_load) AS load_Total

                FROM mandovi.loadd
                WHERE year='2025'
                AND load_type NOT IN ('NO')
                GROUP BY city
            ) l ON l.city = pl.city

            WHERE pl.city IN ('Bangalore','Mysore','Mangalore')
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
                        @ColumnResult(name = "Sep_25", type = Double.class),
                        @ColumnResult(name = "Oct_25", type = Double.class),
                        @ColumnResult(name = "Nov_25", type = Double.class),
                        @ColumnResult(name = "Dec_25", type = Double.class),
                        @ColumnResult(name = "Jan_25", type = Double.class),
                        @ColumnResult(name = "FY_2025_26", type = Double.class),
                        @ColumnResult(name = "load_Apr", type = Double.class),
                        @ColumnResult(name = "load_May", type = Double.class),
                        @ColumnResult(name = "load_Jun", type = Double.class),
                        @ColumnResult(name = "load_Jul", type = Double.class),
                        @ColumnResult(name = "load_Aug", type = Double.class),
                        @ColumnResult(name = "load_Sep", type = Double.class),
                        @ColumnResult(name = "load_Oct", type = Double.class),
                        @ColumnResult(name = "load_Nov", type = Double.class),
                        @ColumnResult(name = "load_Dec", type = Double.class),
                        @ColumnResult(name = "load_Jan", type = Double.class),
                        @ColumnResult(name = "load_Total", type = Double.class),
                        @ColumnResult(name = "Apr25_per_100k", type = Double.class),
                        @ColumnResult(name = "May25_per_100k", type = Double.class),
                        @ColumnResult(name = "Jun25_per_100k", type = Double.class),
                        @ColumnResult(name = "Jul25_per_100k", type = Double.class),
                        @ColumnResult(name = "Aug25_per_100k", type = Double.class),
                        @ColumnResult(name = "Sep25_per_100k", type = Double.class),
                        @ColumnResult(name = "Oct25_per_100k", type = Double.class),
                        @ColumnResult(name = "Nov25_per_100k", type = Double.class),
                        @ColumnResult(name = "Dec25_per_100k", type = Double.class),
                        @ColumnResult(name = "Jan25_per_100k", type = Double.class),
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
            t.`Sep-25` AS Sep_25,
            t.`Oct-25` AS Oct_25,
            t.`Nov-25` AS Nov_25,
            t.`Dec-25` AS Dec_25,
            t.`Jan-25` AS Jan_25,
            t.`2025-26` AS FY_2025_26,

            t.load_Apr,
            t.load_May,
            t.load_Jun,
            t.load_Jul,
            t.load_Aug,
            t.load_Sep,
            t.load_Oct,
            t.load_Nov,
            t.load_Dec,
            t.load_Jan,
            t.load_Total,

            ROUND((t.`Apr-25` / NULLIF(t.load_Apr,0))*100000,3) AS Apr25_per_100k,
            ROUND((t.`May-25` / NULLIF(t.load_May,0))*100000,3) AS May25_per_100k,
            ROUND((t.`Jun-25` / NULLIF(t.load_Jun,0))*100000,3) AS Jun25_per_100k,
            ROUND((t.`Jul-25` / NULLIF(t.load_Jul,0))*100000,3) AS Jul25_per_100k,
            ROUND((t.`Aug-25` / NULLIF(t.load_Aug,0))*100000,3) AS Aug25_per_100k,
            ROUND((t.`Sep-25` / NULLIF(t.load_Sep,0))*100000,3) AS Sep25_per_100k,
            ROUND((t.`Oct-25` / NULLIF(t.load_Oct,0))*100000,3) AS Oct25_per_100k,
            ROUND((t.`Nov-25` / NULLIF(t.load_Nov,0))*100000,3) AS Nov25_per_100k,
            ROUND((t.`Dec-25` / NULLIF(t.load_Dec,0))*100000,3) AS Dec25_per_100k,
            ROUND((t.`Jan-25` / NULLIF(t.load_Jan,0))*100000,3) AS Jan25_per_100k,
            ROUND((t.`Total_25` / NULLIF(t.load_Total,0))*100000,3) AS Total25_per_100k

        FROM (
            SELECT
                pl.city,
                pl.branch,

                SUM(pl.apr_24) AS `Apr-24`,
                SUM(pl.may_24) AS `May-24`,
                SUM(pl.jun_24) AS `Jun-24`,
                SUM(pl.jul_24) AS `Jul-24`,
                (SUM(pl.apr_24)+SUM(pl.may_24)+SUM(pl.jun_24)+SUM(pl.jul_24)) AS `Total_24`,

                SUM(pl.apr_25) AS `Apr-25`,
                SUM(pl.may_25) AS `May-25`,
                SUM(pl.jun_25) AS `Jun-25`,
                SUM(pl.jul_25) AS `Jul-25`,
                SUM(pl.aug_25) AS `Aug-25`,
                SUM(pl.sep_25) AS `Sep-25`,
                SUM(pl.oct_25) AS `Oct-25`,
                SUM(pl.nov_25) AS `Nov-25`,
                SUM(pl.dec_25) AS `Dec-25`,
                SUM(pl.jan_25) AS `Jan-25`,

                (SUM(pl.apr_25)+SUM(pl.may_25)+SUM(pl.jun_25)+SUM(pl.jul_25)+
                 SUM(pl.aug_25)+SUM(pl.sep_25)+SUM(pl.oct_25)+SUM(pl.nov_25)+
                 SUM(pl.dec_25)+SUM(pl.jan_25)) AS `Total_25`,

                SUM(pl.`2025_26`) AS `2025-26`,

                MAX(COALESCE(l.load_Apr,0)) AS load_Apr,
                MAX(COALESCE(l.load_May,0)) AS load_May,
                MAX(COALESCE(l.load_Jun,0)) AS load_Jun,
                MAX(COALESCE(l.load_Jul,0)) AS load_Jul,
                MAX(COALESCE(l.load_Aug,0)) AS load_Aug,
                MAX(COALESCE(l.load_Sep,0)) AS load_Sep,
                MAX(COALESCE(l.load_Oct,0)) AS load_Oct,
                MAX(COALESCE(l.load_Nov,0)) AS load_Nov,
                MAX(COALESCE(l.load_Dec,0)) AS load_Dec,
                MAX(COALESCE(l.load_Jan,0)) AS load_Jan,
                MAX(COALESCE(l.load_Total,0)) AS load_Total

            FROM mandovi.profit_loss pl

            LEFT JOIN (
                SELECT
                    branch,

                    SUM(CASE WHEN month='Apr' THEN service_load END) AS load_Apr,
                    SUM(CASE WHEN month='May' THEN service_load END) AS load_May,
                    SUM(CASE WHEN month='Jun' THEN service_load END) AS load_Jun,
                    SUM(CASE WHEN month='Jul' THEN service_load END) AS load_Jul,
                    SUM(CASE WHEN month='Aug' THEN service_load END) AS load_Aug,
                    SUM(CASE WHEN month='Sep' THEN service_load END) AS load_Sep,
                    SUM(CASE WHEN month='Oct' THEN service_load END) AS load_Oct,
                    SUM(CASE WHEN month='Nov' THEN service_load END) AS load_Nov,
                    SUM(CASE WHEN month='Dec' THEN service_load END) AS load_Dec,
                    SUM(CASE WHEN month='Jan' THEN service_load END) AS load_Jan,

                    SUM(service_load) AS load_Total

                FROM mandovi.loadd
                WHERE year='2025'
                AND load_type NOT IN ('NO')
                GROUP BY branch
            ) l ON l.branch = pl.branch

            WHERE pl.branch IS NOT NULL
             AND pl.branch <> ''
             AND (:cities IS NULL OR pl.city IN (:cities))
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
                        @ColumnResult(name = "Sep_25", type = Double.class),
                        @ColumnResult(name = "Oct_25", type = Double.class),
                        @ColumnResult(name = "Nov_25", type = Double.class),
                        @ColumnResult(name = "Dec_25", type = Double.class),
                        @ColumnResult(name = "Jan_25", type = Double.class),
                        @ColumnResult(name = "FY_2025_26", type = Double.class),
                        @ColumnResult(name = "load_Apr", type = Double.class),
                        @ColumnResult(name = "load_May", type = Double.class),
                        @ColumnResult(name = "load_Jun", type = Double.class),
                        @ColumnResult(name = "load_Jul", type = Double.class),
                        @ColumnResult(name = "load_Aug", type = Double.class),
                        @ColumnResult(name = "load_Sep", type = Double.class),
                        @ColumnResult(name = "load_Oct", type = Double.class),
                        @ColumnResult(name = "load_Nov", type = Double.class),
                        @ColumnResult(name = "load_Dec", type = Double.class),
                        @ColumnResult(name = "load_Jan", type = Double.class),
                        @ColumnResult(name = "load_Total", type = Double.class),
                        @ColumnResult(name = "Apr25_per_100k", type = Double.class),
                        @ColumnResult(name = "May25_per_100k", type = Double.class),
                        @ColumnResult(name = "Jun25_per_100k", type = Double.class),
                        @ColumnResult(name = "Jul25_per_100k", type = Double.class),
                        @ColumnResult(name = "Aug25_per_100k", type = Double.class),
                        @ColumnResult(name = "Sep25_per_100k", type = Double.class),
                        @ColumnResult(name = "Oct25_per_100k", type = Double.class),
                        @ColumnResult(name = "Nov25_per_100k", type = Double.class),
                        @ColumnResult(name = "Dec25_per_100k", type = Double.class),
                        @ColumnResult(name = "Jan25_per_100k", type = Double.class),
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
