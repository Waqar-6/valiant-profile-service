package com.wfarooq.profile_service.controller;

import com.wfarooq.profile_service.constants.ProfileConstants;
import com.wfarooq.profile_service.dto.requests.CreateNormalUserProfileRequest;
import com.wfarooq.profile_service.dto.requests.CreateBreederProfileRequest;
import com.wfarooq.profile_service.dto.response.BaseProfileResponseDto;
import com.wfarooq.profile_service.dto.response.BreederProfileResponseDto;
import com.wfarooq.profile_service.dto.response.NormalUserProfileResponseDto;
import com.wfarooq.profile_service.dto.response.ResponseDto;
import com.wfarooq.profile_service.service.IProfileService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ProfileController {

    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);
    private final IProfileService profileService;

    public ProfileController(IProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/normalUserProfile")
    public ResponseEntity<ResponseDto> createBaseProfile(@Valid @RequestBody CreateNormalUserProfileRequest request) {
        log.info("create normal user profile request {}", request);
        profileService.createNormalUserProfile(request);
        log.info("new normal user profile created for user {}", request.getFirstName());
        return new ResponseEntity<>(new ResponseDto(ProfileConstants.STATUS_201, ProfileConstants.MESSAGE_201), HttpStatus.CREATED);
    }

    @PostMapping("/breederProfile")
    public ResponseEntity<ResponseDto> createBreederProfile (@Valid @RequestBody CreateBreederProfileRequest request) {
        log.info("create breeder profile request {}", request);
        profileService.createBreederProfile(request);
        log.info("new Breeder profile created for user {}", request.getFirstName());
        return new ResponseEntity<>(new ResponseDto(ProfileConstants.STATUS_201, ProfileConstants.MESSAGE_201), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BaseProfileResponseDto>> fetchAllProfiles () {
        log.info("Received request to fetch all profiles");
        List<BaseProfileResponseDto> allProfiles = profileService.fetchAllProfiles();
        log.info("Successfully fetched all profiles. Total count: {}", allProfiles.size());
        return new ResponseEntity<>(allProfiles, HttpStatus.OK);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<BaseProfileResponseDto> fetchProfileById (@PathVariable UUID profileId) {
        log.info("Received request to fetch profile by ID: {}", profileId);
        BaseProfileResponseDto profile = profileService.fetchProfileById(profileId);
        log.info("Successfully fetched profile: {}", profileId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping("/breederProfiles")
    public ResponseEntity<List<BreederProfileResponseDto>> fetchAllBreederProfiles () {
        log.info("Received request to fetch all breeder profiles");
        List<BreederProfileResponseDto> profiles = profileService.fetchAllBreeders();
        log.info("Successfully fetched breeder profiles. Total count: {}", profiles.size());
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @GetMapping("/normalUserProfiles")
    public ResponseEntity<List<NormalUserProfileResponseDto>> fetchAllNormalUserProfiles () {
        log.info("Received request to fetch all normal user profiles");
        List<NormalUserProfileResponseDto> profiles = profileService.fetchAllNormalUsers();
        log.info("Successfully fetched normal user profiles. Total count: {}", profiles.size());
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }
}
