package com.kevin.dto;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Author: Kevin
 * @Date: 2022/8/27 11:02
 * @Description: 身份信息
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "identity_info")
@IdClass(value = IdentityInfoKey.class)
public class IdentityInfo implements Serializable {
    @Id
    @Column(name = "app_id")
    private String appId;

    @Id
    @Column(name = "app_secret")
    private String appSecret;

    @Id
    @Column(name = "open_id")
    private String openId;

    @Basic
    @Column(name = "public_id")
    private String publicId;

    @Basic
    @Column(name = "latitude")
    private String latitude;

    @Basic
    @Column(name = "longitude")
    private String longitude;
    @Basic
    @Column(name = "precise")
    private String precise;

    @Basic
    @Column(name = "country")
    private String country;

    @Basic
    @Column(name = "province")
    private String province;

    @Basic
    @Column(name = "city")
    private String city;

    @Basic
    @Column(name = "district")
    private String district;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "status")
    private Integer status = 0;

    @Basic
    @Column(name = "adcode")
    private String adcode;

    @Column(name = "create_time", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createTime;

    @Column(name = "update_time")
    @LastModifiedDate
    private LocalDateTime updateTime;
}
