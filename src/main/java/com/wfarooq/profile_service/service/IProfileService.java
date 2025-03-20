package com.wfarooq.profile_service.service;

import com.wfarooq.profile_service.dto.requests.CreateNormalUserProfileRequest;
import com.wfarooq.profile_service.dto.requests.CreateBreederProfileRequest;
import com.wfarooq.profile_service.dto.response.BaseProfileResponseDto;
import com.wfarooq.profile_service.dto.response.BreederProfileResponseDto;
import com.wfarooq.profile_service.entity.BaseProfile;

import java.util.List;
import java.util.UUID;

public interface IProfileService {


    void createNormalUserProfile(CreateNormalUserProfileRequest createBaseProfileRequest);
    void createBreederProfile (CreateBreederProfileRequest createBreederProfileRequest);

    List<BaseProfileResponseDto> fetchAllProfiles ();
    BaseProfileResponseDto fetchProfileById (UUID profileId);
    List<BreederProfileResponseDto> fetchAllBreeders ();
}
