package com.wfarooq.profile_service.mapper;

import com.wfarooq.profile_service.constants.ProfileType;
import com.wfarooq.profile_service.dto.requests.CreateBaseProfileRequest;
import com.wfarooq.profile_service.dto.requests.CreateNormalUserProfileRequest;
import com.wfarooq.profile_service.dto.requests.CreateBreederProfileRequest;
import com.wfarooq.profile_service.entity.BaseProfile;
import com.wfarooq.profile_service.entity.BreederProfile;
import com.wfarooq.profile_service.entity.NormalUserProfile;

public final class ProfileMapper {

    private ProfileMapper () {}

    public static BaseProfile mapToBaseProfile (CreateBaseProfileRequest request, BaseProfile baseProfile) {
        baseProfile.setFirstName(request.getFirstName());
        baseProfile.setLastName(request.getLastName());
        baseProfile.setBio(request.getBio());
        baseProfile.setProfileType(request.getProfileType());
        return baseProfile;
    }

    public static NormalUserProfile mapToNormalUserProfile (CreateNormalUserProfileRequest request, NormalUserProfile normalUserProfile) {
        normalUserProfile.setFirstName(request.getFirstName());
        normalUserProfile.setLastName(request.getLastName());
        normalUserProfile.setBio(request.getBio());
        normalUserProfile.setProfileType(request.getProfileType());
        return normalUserProfile;
    }

    public static BreederProfile mapToBreederProfile (CreateBreederProfileRequest request, BreederProfile breederProfile) {
        breederProfile.setFirstName(request.getFirstName());
        breederProfile.setLastName(request.getLastName());
        breederProfile.setBio(request.getBio());
        breederProfile.setProfileType(request.getProfileType());
        breederProfile.setKennelName(request.getKennelName());
        breederProfile.setWebsite(request.getWebsite());
        return breederProfile;
    }
}
