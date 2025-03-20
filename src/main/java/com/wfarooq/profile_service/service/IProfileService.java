package com.wfarooq.profile_service.service;

import com.wfarooq.profile_service.dto.requests.CreateNormalUserProfileRequest;
import com.wfarooq.profile_service.dto.requests.CreateBreederProfileRequest;
import com.wfarooq.profile_service.dto.response.BaseProfileResponseDto;
import com.wfarooq.profile_service.entity.BaseProfile;

import java.util.List;

public interface IProfileService {


    void createNormalUserProfile(CreateNormalUserProfileRequest createBaseProfileRequest);
    void createBreederProfile (CreateBreederProfileRequest createBreederProfileRequest);

    List<BaseProfileResponseDto> fetchAllProfiles ();
}
