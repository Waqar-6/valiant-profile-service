package com.wfarooq.profile_service.mapper;

import com.wfarooq.profile_service.dto.requests.CreateBaseProfileRequest;
import com.wfarooq.profile_service.entity.BaseProfile;

public final class ProfileMapper {

    private ProfileMapper () {}

    public static BaseProfile mapToBaseProfile (CreateBaseProfileRequest request, BaseProfile baseProfile) {
        baseProfile.setFirstName(request.firstName());
        baseProfile.setLastName(request.lastName());
        baseProfile.setBio(request.bio());
        baseProfile.setProfileType(request.profileType());
        return baseProfile;
    }
}
