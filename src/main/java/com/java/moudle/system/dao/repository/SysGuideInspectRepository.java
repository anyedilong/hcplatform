package com.java.moudle.system.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.moudle.system.domain.SysGuideInspect;

@Repository
public interface SysGuideInspectRepository extends JpaRepository<SysGuideInspect, String>{

}
