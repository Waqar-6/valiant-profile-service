package com.wfarooq.profile_service.service;

import com.wfarooq.profile_service.dto.requests.CreateBaseProfileRequest;

public interface IProfileService {


    void createBaseProfile (CreateBaseProfileRequest createBaseProfileRequest);
}
