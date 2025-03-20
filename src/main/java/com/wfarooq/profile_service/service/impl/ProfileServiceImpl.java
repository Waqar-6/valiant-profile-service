package com.wfarooq.profile_service.service.impl;

import com.wfarooq.profile_service.dto.requests.CreateNormalUserProfileRequest;
import com.wfarooq.profile_service.dto.requests.CreateBreederProfileRequest;
import com.wfarooq.profile_service.entity.BaseProfile;
import com.wfarooq.profile_service.entity.BreederProfile;
import com.wfarooq.profile_service.entity.NormalUserProfile;
import com.wfarooq.profile_service.mapper.ProfileMapper;
import com.wfarooq.profile_service.repository.BaseProfileRepository;
import com.wfarooq.profile_service.repository.BreederProfileRepository;
import com.wfarooq.profile_service.repository.NormalUserRepository;
import com.wfarooq.profile_service.service.IProfileService;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements IProfileService {

    private final BaseProfileRepository profileRepository;
    private final BreederProfileRepository breederProfileRepository;
    private final NormalUserRepository normalUserRepository;

    public ProfileServiceImpl(BaseProfileRepository profileRepository, BreederProfileRepository breederProfileRepository, NormalUserRepository normalUserRepository) {
        this.profileRepository = profileRepository;
        this.breederProfileRepository = breederProfileRepository;
        this.normalUserRepository = normalUserRepository;
    }


    @Override
    public void createNormalUserProfile(CreateNormalUserProfileRequest createNormalUserProfileRequest) {
        NormalUserProfile newNormalUserProfile = ProfileMapper.mapToNormalUserProfile(createNormalUserProfileRequest, new NormalUserProfile());
        normalUserRepository.save(newNormalUserProfile);
    }

    @Override
    public void createBreederProfile(CreateBreederProfileRequest createBreederProfileRequest) {
        BreederProfile newBreederProfile = ProfileMapper.mapToBreederProfile(createBreederProfileRequest, new BreederProfile());
        breederProfileRepository.save(newBreederProfile);
    }
}
