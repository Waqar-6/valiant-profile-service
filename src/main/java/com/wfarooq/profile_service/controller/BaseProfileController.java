package com.wfarooq.profile_service.controller;

import com.wfarooq.profile_service.constants.ProfileConstants;
import com.wfarooq.profile_service.dto.requests.CreateBaseProfileRequest;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class BaseProfileController {

    private static final Logger log = LoggerFactory.getLogger(BaseProfileController.class);
    private final IProfileService profileService;

    public BaseProfileController(IProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/baseProfile")
    public ResponseEntity<ResponseDto> createBaseProfile(@Valid @RequestBody CreateBaseProfileRequest request) {
        log.info("create base profile request {}", request);
        profileService.createBaseProfile(request);
        log.info("new base profile created for user {}", request.firstName());
        return new ResponseEntity<>(new ResponseDto(ProfileConstants.STATUS_201, ProfileConstants.MESSAGE_201), HttpStatus.CREATED);
    }
}
