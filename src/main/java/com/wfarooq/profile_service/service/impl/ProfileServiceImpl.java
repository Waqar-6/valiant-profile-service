package com.wfarooq.profile_service.service.impl;

import com.wfarooq.profile_service.constants.ProfileType;
import com.wfarooq.profile_service.dto.requests.CreateNormalUserProfileRequest;
import com.wfarooq.profile_service.dto.requests.CreateBreederProfileRequest;
import com.wfarooq.profile_service.dto.response.BaseProfileResponseDto;
import com.wfarooq.profile_service.dto.response.BreederProfileResponseDto;
import com.wfarooq.profile_service.dto.response.NormalUserProfileResponseDto;
import com.wfarooq.profile_service.entity.BaseProfile;
import com.wfarooq.profile_service.entity.BreederProfile;
import com.wfarooq.profile_service.entity.NormalUserProfile;
import com.wfarooq.profile_service.exception.ResourceNotFoundException;
import com.wfarooq.profile_service.mapper.ProfileMapper;
import com.wfarooq.profile_service.repository.BaseProfileRepository;
import com.wfarooq.profile_service.repository.BreederProfileRepository;
import com.wfarooq.profile_service.repository.NormalUserRepository;
import com.wfarooq.profile_service.service.IProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    @Override
    public List<BaseProfileResponseDto> fetchAllProfiles() {

        List<BaseProfile> allProfiles = profileRepository.findAll();

        return allProfiles.stream().map(profile -> {
            if (profile.getProfileType().equals(ProfileType.NORMAL_USER_PROFILE)) {
                return ProfileMapper.mapToNormalUserProfileResponse((NormalUserProfile) profile, new NormalUserProfileResponseDto());
            }

            if (profile.getProfileType().equals(ProfileType.BREEDER_PROFILE)) {
                return ProfileMapper.mapToBreederProfileResponse((BreederProfile) profile, new BreederProfileResponseDto());
            }
            return null;
        }).toList();
    }

    @Override
    public BaseProfileResponseDto fetchProfileById(UUID profileId) {
       BaseProfile profile = profileRepository.findById(profileId).orElseThrow(() -> new ResourceNotFoundException("Profile", "id", profileId.toString()));

       if (profile.getProfileType().equals(ProfileType.NORMAL_USER_PROFILE)) {
           return ProfileMapper.mapToNormalUserProfileResponse((NormalUserProfile) profile, new NormalUserProfileResponseDto());
       }

        if (profile.getProfileType().equals(ProfileType.BREEDER_PROFILE)) {
            return ProfileMapper.mapToBreederProfileResponse((BreederProfile) profile, new BreederProfileResponseDto());
        }

       return null;
    }

    @Override
    public List<BreederProfileResponseDto> fetchAllBreeders() {
        List<BreederProfile> breederProfiles = breederProfileRepository.findAll();
        return breederProfiles.stream()
                .map(profile -> ProfileMapper.mapToBreederProfileResponse(profile, new BreederProfileResponseDto())).toList();
    }

    @Override
    public List<NormalUserProfileResponseDto> fetchAllNormalUsers() {
        List<NormalUserProfile> normalUserProfiles = normalUserRepository.findAll();
        return normalUserProfiles.stream()
                .map(profile -> ProfileMapper.mapToNormalUserProfileResponse(profile, new NormalUserProfileResponseDto())).toList();
    }
}
