package com.wfarooq.profile_service.dto.response;

import com.wfarooq.profile_service.constants.ProfileType;

import java.util.UUID;

public class NormalUserProfileResponseDto extends BaseProfileResponseDto{

    public NormalUserProfileResponseDto(UUID id, String firstName, String lastName, String bio, ProfileType profileType) {
        super(id, firstName, lastName, bio, profileType);
    }

    public NormalUserProfileResponseDto() {
    }
}
