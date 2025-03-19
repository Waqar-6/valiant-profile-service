package com.wfarooq.profile_service.mapper;

import com.wfarooq.profile_service.dto.requests.CreateBaseProfileRequest;
import com.wfarooq.profile_service.dto.requests.CreateBreederProfileRequest;
import com.wfarooq.profile_service.entity.BaseProfile;
import com.wfarooq.profile_service.entity.BreederProfile;

public final class ProfileMapper {

    private ProfileMapper () {}

    public static BaseProfile mapToBaseProfile (CreateBaseProfileRequest request, BaseProfile baseProfile) {
        baseProfile.setFirstName(request.getFirstName());
        baseProfile.setLastName(request.getLastName());
        baseProfile.setBio(request.getBio());
        baseProfile.setProfileType(request.getProfileType());
        return baseProfile;
    }

    public static BreederProfile mapToBreederProfile (CreateBreederProfileRequest request, BreederProfile breederProfile) {
        mapToBaseProfile(request, breederProfile);
        breederProfile.setKennelName(request.getKennelName());
        breederProfile.setWebsite(request.getWebsite());
        return breederProfile;
    }
}
