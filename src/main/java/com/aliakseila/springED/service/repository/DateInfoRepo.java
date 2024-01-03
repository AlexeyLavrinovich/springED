package com.aliakseila.springED.service.repository;

import com.aliakseila.springED.model.entity.DateInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateInfoRepo extends JpaRepository<DateInfo, Long> {
}
