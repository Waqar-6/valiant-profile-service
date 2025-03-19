package com.wfarooq.profile_service.service;

import com.wfarooq.profile_service.dto.requests.CreateBaseProfileRequest;
import com.wfarooq.profile_service.dto.requests.CreateBreederProfileRequest;

public interface IProfileService {


    void createBaseProfile (CreateBaseProfileRequest createBaseProfileRequest);
    void createBreederProfile (CreateBreederProfileRequest createBreederProfileRequest);
}
