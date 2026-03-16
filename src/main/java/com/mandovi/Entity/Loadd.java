package com.mandovi.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@SqlResultSetMapping(
        name = "ProductSummaryCityWiseMapping",
        classes = @ConstructorResult(
                targetClass = com.mandovi.DTO.ProductivitySummaryDTO.class,
                columns = {
                        @ColumnResult(name = "city", type = String.class),
                        @ColumnResult(name = "month_name", type = String.class),
                        @ColumnResult(name = "total_service_bay_utilized", type = Double.class),
                        @ColumnResult(name = "total_records", type = Double.class),
                        @ColumnResult(name = "free_percentage", type = Double.class),
                        @ColumnResult(name = "free_count", type = Double.class),
                        @ColumnResult(name = "pms_percentage", type = Double.class),
                        @ColumnResult(name = "pms_count", type = Double.class),
                        @ColumnResult(name = "rr_percentage", type = Double.class),
                        @ColumnResult(name = "rr_count", type = Double.class),
                        @ColumnResult(name = "others_percentage", type = Double.class),
                        @ColumnResult(name = "others_count", type = Double.class),
                        @ColumnResult(name = "total_bodyshop_bay_utilized", type = Double.class),
                        @ColumnResult(name = "bodyshop_count", type = Double.class),
                        @ColumnResult(name = "bodyshop_percentage", type = Double.class)
                }
        )
)
@NamedNativeQuery(
        name = "Loadd.getProductSummaryCityWise",
        query = """
               SELECT
                   l.city AS city,
                   NULL AS month_name,
                   (SELECT SUM(p.service_utilized_bay)
                      FROM productivity p
                      WHERE p.city = l.city) AS total_service_bay_utilized,

                   SUM(CASE WHEN l.load_type IN ('FREE SERVICE','PMS','RR','OTHERS') 
                            THEN l.service_load ELSE 0 END) AS total_records,

                   0.0 AS free_percentage,
                   SUM(CASE WHEN l.load_type = 'FREE SERVICE' 
                            THEN l.service_load ELSE 0 END) AS free_count,

                   0.0 AS pms_percentage,
                   SUM(CASE WHEN l.load_type = 'PMS' 
                            THEN l.service_load ELSE 0 END) AS pms_count,

                   0.0 AS rr_percentage,
                   SUM(CASE WHEN l.load_type = 'RR' 
                            THEN l.service_load ELSE 0 END) AS rr_count,

                   0.0 AS others_percentage,
                   SUM(CASE WHEN l.load_type = 'OTHERS' 
                            THEN l.service_load ELSE 0 END) AS others_count,

                   (SELECT SUM(p.body_shop_utilized_bay)
                      FROM productivity p
                      WHERE p.city = l.city) AS total_bodyshop_bay_utilized,

                   SUM(CASE WHEN l.load_type = 'BODYSHOP' 
                            THEN l.service_load ELSE 0 END) AS bodyshop_count,

                   0.0 AS bodyshop_percentage
               FROM loadd l
               WHERE (:months IS NULL OR l.month IN (:months))
                 AND (:years IS NULL OR l.year IN (:years))
               GROUP BY l.city
               """,
        resultSetMapping = "ProductSummaryCityWiseMapping"
)

@Entity
@Table(name = "loadd")
public class Loadd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loadSINo")
    private Integer loadSINo;

    @Column(name = "service_type_code")
    private String serviceTypeCode;

    @Column(name = "year")
    private String year;

    @Column(name = "month")
    private String month;

    @Column(name = "service_load")
    private Integer serviceLoad;

    @Column(name = "channel")
    private String channel;

    @Column(name = "city")
    private String city;

    @Column(name = "branch")
    private String branch;

    @Column(name = "financial_year")
    private String financialYear;

    @Column(name = "load_type")
    private String loadType;

    @Column(name = "qtr_wise")
    private String qtrWise;

    @Column(name = "half_year")
    private String halfYear;

    public Loadd() {
    }

    public Loadd(Integer loadSINo, String serviceTypeCode, String year, String month, Integer serviceLoad, String channel, String city, String branch, String financialYear, String loadType, String qtrWise, String halfYear) {
        this.loadSINo = loadSINo;
        this.serviceTypeCode = serviceTypeCode;
        this.year = year;
        this.month = month;
        this.serviceLoad = serviceLoad;
        this.channel = channel;
        this.city = city;
        this.branch = branch;
        this.financialYear = financialYear;
        this.loadType = loadType;
        this.qtrWise = qtrWise;
        this.halfYear = halfYear;
    }

    public Integer getLoadSINo() {
        return loadSINo;
    }

    public void setLoadSINo(Integer loadSINo) {
        this.loadSINo = loadSINo;
    }

    public String getServiceTypeCode() {
        return serviceTypeCode;
    }

    public void setServiceTypeCode(String serviceTypeCode) {
        this.serviceTypeCode = serviceTypeCode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getServiceLoad() {
        return serviceLoad;
    }

    public void setServiceLoad(Integer serviceLoad) {
        this.serviceLoad = serviceLoad;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public String getQtrWise() {
        return qtrWise;
    }

    public void setQtrWise(String qtrWise) {
        this.qtrWise = qtrWise;
    }

    public String getHalfYear() {
        return halfYear;
    }

    public void setHalfYear(String halfYear) {
        this.halfYear = halfYear;
    }

    @Override
    public String toString() {
        return "Loadd{" +
                "loadSINo=" + loadSINo +
                ", serviceTypeCode='" + serviceTypeCode + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", serviceLoad=" + serviceLoad +
                ", channel='" + channel + '\'' +
                ", city='" + city + '\'' +
                ", branch='" + branch + '\'' +
                ", financialYear='" + financialYear + '\'' +
                ", loadType='" + loadType + '\'' +
                ", qtrWise='" + qtrWise + '\'' +
                ", halfYear='" + halfYear + '\'' +
                '}';
    }
}
