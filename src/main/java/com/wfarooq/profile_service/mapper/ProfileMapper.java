package com.wfarooq.profile_service.mapper;

import com.wfarooq.profile_service.dto.requests.CreateBaseProfileRequest;
import com.wfarooq.profile_service.dto.requests.CreateNormalUserProfileRequest;
import com.wfarooq.profile_service.dto.requests.CreateBreederProfileRequest;
import com.wfarooq.profile_service.dto.response.BreederProfileResponseDto;
import com.wfarooq.profile_service.dto.response.NormalUserProfileResponseDto;
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

    public static NormalUserProfileResponseDto mapToNormalUserProfileResponse (NormalUserProfile normalUserProfile, NormalUserProfileResponseDto responseDto) {
        responseDto.setId(normalUserProfile.getId());
        responseDto.setFirstName(normalUserProfile.getFirstName());
        responseDto.setLastName(normalUserProfile.getLastName());
        responseDto.setBio(normalUserProfile.getBio());
        responseDto.setProfileType(normalUserProfile.getProfileType());
        return responseDto;
    }

    public static BreederProfileResponseDto mapToBreederProfileResponse (BreederProfile breederUserProfile, BreederProfileResponseDto responseDto) {
        responseDto.setId(breederUserProfile.getId());
        responseDto.setFirstName(breederUserProfile.getFirstName());
        responseDto.setLastName(breederUserProfile.getLastName());
        responseDto.setBio(breederUserProfile.getBio());
        responseDto.setProfileType(breederUserProfile.getProfileType());
        responseDto.setKennelName(breederUserProfile.getKennelName());
        responseDto.setWebsite(breederUserProfile.getWebsite());
        return responseDto;
    }

}
