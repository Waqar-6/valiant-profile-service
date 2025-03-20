package com.wfarooq.profile_service.repository;

import com.wfarooq.profile_service.entity.BaseProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BaseProfileRepository extends JpaRepository<BaseProfile, UUID> {

}
