package com.wfarooq.profile_service.service;

import com.wfarooq.profile_service.dto.requests.CreateNormalUserProfileRequest;
import com.wfarooq.profile_service.dto.requests.CreateBreederProfileRequest;

public interface IProfileService {


    void createNormalUserProfile(CreateNormalUserProfileRequest createBaseProfileRequest);
    void createBreederProfile (CreateBreederProfileRequest createBreederProfileRequest);
}
