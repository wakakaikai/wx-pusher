package com.kevin.repository;

import com.kevin.dto.AiQa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Kevin
 * @Date: 2023-02-10 00:02
 * @Description: AI问答持久层
 */
public interface AiQaRepository extends JpaRepository<AiQa, Integer> {
}
