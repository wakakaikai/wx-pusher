package com.kevin.repository;


import com.kevin.dto.IdentityInfo;
import com.kevin.dto.IdentityInfoKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Kevin
 * @Date: 2022/9/9 16:51
 * @Description: 身份信息存储
 */
public interface IdentityInfoRepository extends JpaRepository<IdentityInfo, IdentityInfoKey> {
}
