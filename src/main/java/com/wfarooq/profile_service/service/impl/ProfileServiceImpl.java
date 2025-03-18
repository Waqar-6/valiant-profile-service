package com.wfarooq.profile_service.service.impl;

import com.wfarooq.profile_service.dto.requests.CreateBaseProfileRequest;
import com.wfarooq.profile_service.entity.BaseProfile;
import com.wfarooq.profile_service.mapper.ProfileMapper;
import com.wfarooq.profile_service.repository.ProfileRepository;
import com.wfarooq.profile_service.service.IProfileService;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements IProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


    @Override
    public void createBaseProfile(CreateBaseProfileRequest createBaseProfileRequest) {
        BaseProfile newBaseProfile = ProfileMapper.mapToBaseProfile(createBaseProfileRequest, new BaseProfile());
        profileRepository.save(newBaseProfile);
    }
}
