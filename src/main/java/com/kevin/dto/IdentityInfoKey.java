package com.kevin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @Author: Kevin
 * @Date: 2022-09-09 00:08
 * @Description: 身份信息的主键
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdentityInfoKey implements Serializable {

    @Id
    @Column(name = "app_id")
    private String appId;

    @Id
    @Column(name = "app_secret")
    private String appSecret;

    @Id
    @Column(name = "open_id")
    private String openId;
}
