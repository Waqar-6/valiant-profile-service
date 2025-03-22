package com.wfarooq.profile_service.entity;

import com.wfarooq.profile_service.constants.ProfileType;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn
public class NormalUserProfile extends BaseProfile{

    public NormalUserProfile(String firstName, String lastName, String bio, ProfileType profileType) {
        super(firstName, lastName, bio, profileType);
    }

    public NormalUserProfile() {
    }
}
