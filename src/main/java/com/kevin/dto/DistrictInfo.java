package com.kevin.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Author: Kevin
 * @Date: 2022-09-08 23:58
 * @Description: 全国各个地区的districtCode、经纬度信息
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "weather_district_id")
public class DistrictInfo {
    @Id
    @Column(name = "area_code")
    private Integer areaCode;

    @Basic
    @Column(name = "district_code")
    private Integer districtCode;

    @Basic
    @Column(name = "city_geocode")
    private Integer cityGeocode;

    @Basic
    @Column(name = "city")
    private String city;

    @Basic
    @Column(name = "district_geocode")
    private Integer districtGeocode;

    @Basic
    @Column(name = "district")
    private String district;

    @Basic
    @Column(name = "lon")
    private String lon;

    @Basic
    @Column(name = "lat")
    private String lat;

    @Basic
    @Column(name = "sta_fc")
    private String staFc;

    @Basic
    @Column(name = "sta_rt")
    private String staRt;

    @Basic
    @Column(name = "province")
    private String province;

    @Basic
    @Column(name = "fc_lon")
    private String fcLon;

    @Basic
    @Column(name = "fc_lat")
    private String fcLat;

    @Basic
    @Column(name = "rt_lon")
    private String rtLon;

    @Basic
    @Column(name = "rt_lat")
    private String rtLat;

    @Basic
    @Column(name = "origin_areacode")
    private Integer originAreacode;

    @Basic
    @Column(name = "exclude")
    private Integer exclude;
}
