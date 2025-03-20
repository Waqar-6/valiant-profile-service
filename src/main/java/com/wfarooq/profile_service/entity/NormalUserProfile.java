package com.wfarooq.profile_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn
public class NormalUserProfile extends BaseProfile{
}
