package com.wfarooq.profile_service.controller;

import com.wfarooq.profile_service.constants.ProfileConstants;
import com.wfarooq.profile_service.dto.requests.CreateNormalUserProfileRequest;
import com.wfarooq.profile_service.dto.requests.CreateBreederProfileRequest;
import com.wfarooq.profile_service.dto.response.BaseProfileResponseDto;
import com.wfarooq.profile_service.dto.response.ResponseDto;
import com.wfarooq.profile_service.entity.BaseProfile;
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
       List<BaseProfileResponseDto> allProfiles = profileService.fetchAllProfiles();
        return new ResponseEntity<>(allProfiles, HttpStatus.OK);
    }
}
