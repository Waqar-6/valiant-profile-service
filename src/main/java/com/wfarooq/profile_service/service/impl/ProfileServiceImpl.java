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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfileServiceImpl implements IProfileService {

    private static final Logger log = LoggerFactory.getLogger(ProfileServiceImpl.class);
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
        log.info("Creating normal user profile for: {}", createNormalUserProfileRequest.getFirstName());
        NormalUserProfile newNormalUserProfile = ProfileMapper.mapToNormalUserProfile(createNormalUserProfileRequest, new NormalUserProfile());
        normalUserRepository.save(newNormalUserProfile);
        log.info("Successfully saved normal user profile with ID: {}", newNormalUserProfile.getId());
    }

    @Override
    public void createBreederProfile(CreateBreederProfileRequest createBreederProfileRequest) {
        log.info("Creating breeder profile for: {}", createBreederProfileRequest.getFirstName());
        BreederProfile newBreederProfile = ProfileMapper.mapToBreederProfile(createBreederProfileRequest, new BreederProfile());
        breederProfileRepository.save(newBreederProfile);
        log.info("Successfully saved breeder profile with ID: {}", newBreederProfile.getId());
    }

    @Override
    public List<BaseProfileResponseDto> fetchAllProfiles() {

        log.info("Fetching all profiles");
        List<BaseProfile> allProfiles = profileRepository.findAll();

        log.info("Successfully fetched all profiles. Total count: {}", allProfiles.size());
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
        log.info("Fetching profile by ID: {}", profileId);

       BaseProfile profile = profileRepository.findById(profileId).orElseThrow(() -> new ResourceNotFoundException("Profile", "id", profileId.toString()));

        log.info("Successfully fetched profile with ID: {}", profileId);
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
        log.info("Fetching all breeder profiles");
        List<BreederProfile> breederProfiles = breederProfileRepository.findAll();
        log.info("Successfully fetched breeder profiles. Total count: {}", breederProfiles.size());
        return breederProfiles.stream()
                .map(profile -> ProfileMapper.mapToBreederProfileResponse(profile, new BreederProfileResponseDto())).toList();
    }

    @Override
    public List<NormalUserProfileResponseDto> fetchAllNormalUsers() {
        log.info("Fetching all normal user profiles");
        List<NormalUserProfile> normalUserProfiles = normalUserRepository.findAll();
        log.info("Successfully fetched normal user profiles. Total count: {}", normalUserProfiles.size());
        return normalUserProfiles.stream()
                .map(profile -> ProfileMapper.mapToNormalUserProfileResponse(profile, new NormalUserProfileResponseDto())).toList();
    }

    @Override
    public boolean updateNormalUserProfile(CreateNormalUserProfileRequest request, UUID profileId) {
        log.info("Updating normal user profile with the id : {}", profileId);
        NormalUserProfile normalUserProfile = normalUserRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "id", profileId.toString()));

        ProfileMapper.mapToNormalUserProfile(request, normalUserProfile);
        normalUserRepository.save(normalUserProfile);
        log.info("Normal user profile updated and saved with the id : {}", profileId);
        return true;
    }

    @Override
    public boolean updateBreederProfile(CreateBreederProfileRequest request, UUID profileId) {
        log.info("Updating breeder profile with the id : {}", profileId);
        BreederProfile breederProfile = breederProfileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "id", profileId.toString()));
        ProfileMapper.mapToBreederProfile(request, breederProfile);
        breederProfileRepository.save(breederProfile);
        log.info("Breeder profile updated and saved to database with the id : {}", profileId);
        return true;
    }

    @Override
    public boolean deleteProfileById(UUID profileId) {
        log.info("Delete breeder profile with the id : {}", profileId);
        BaseProfile profile = profileRepository.findById(profileId).orElseThrow(() -> new ResourceNotFoundException("Profile", "id", profileId.toString()));
        profileRepository.delete(profile);
        log.info("Breeder profile deleted from database with the id : {}", profileId);
        return true;
    }
}
