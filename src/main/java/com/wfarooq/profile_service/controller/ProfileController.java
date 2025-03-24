package com.wfarooq.profile_service.controller;

import com.wfarooq.profile_service.constants.ProfileConstants;
import com.wfarooq.profile_service.dto.requests.CreateNormalUserProfileRequest;
import com.wfarooq.profile_service.dto.requests.CreateBreederProfileRequest;
import com.wfarooq.profile_service.dto.response.*;
import com.wfarooq.profile_service.service.IProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(
        name = "CRUD REST API for profile service",
        description = "crud functionality for all post, get, put, delete"
)
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/profiles", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ProfileController {

    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);
    private final IProfileService profileService;

    public ProfileController(IProfileService profileService) {
        this.profileService = profileService;
    }

    @Operation(
            summary = "Create Normal User Profile",
            description = "post operation to create a profile for the normal user of the app"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Http Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/normalUserProfile")
    public ResponseEntity<ResponseDto> createNormalUserProfile(@Valid @RequestBody CreateNormalUserProfileRequest request) {
        log.info("create normal user profile request {}", request);
        profileService.createNormalUserProfile(request);
        log.info("new normal user profile created for user {}", request.getFirstName());
        return new ResponseEntity<>(new ResponseDto(ProfileConstants.STATUS_201, ProfileConstants.MESSAGE_201), HttpStatus.CREATED);
    }


    @Operation(
            summary = "Create Dog Breeder User Profile",
            description = "post operation to create a profile for the dog breeder"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Http Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
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


    @Operation(
            summary = "Fetch Profile by id",
            description = "REST API to fetch Profile based on id provided"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Http Status NOT FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }

    )
    @GetMapping("/{profileId}")
    public ResponseEntity<BaseProfileResponseDto> fetchProfileById (@PathVariable UUID profileId) {
        log.info("Received request to fetch profile by ID: {}", profileId);
        BaseProfileResponseDto profile = profileService.fetchProfileById(profileId);
        log.info("Successfully fetched profile: {}", profileId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }



    @Operation(
            summary = "Fetch all dog breeder profiles",
            description = "REST API to fetch all dog breeder profiles"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }

    )

    @GetMapping("/breederProfiles")
    public ResponseEntity<List<BreederProfileResponseDto>> fetchAllBreederProfiles () {
        log.info("Received request to fetch all breeder profiles");
        List<BreederProfileResponseDto> profiles = profileService.fetchAllBreeders();
        log.info("Successfully fetched breeder profiles. Total count: {}", profiles.size());
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @Operation(
            summary = "Fetch all normal user profiles",
            description = "REST API to fetch all normal user profiles"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }

    )

    @GetMapping("/normalUserProfiles")
    public ResponseEntity<List<NormalUserProfileResponseDto>> fetchAllNormalUserProfiles () {
        log.info("Received request to fetch all normal user profiles");
        List<NormalUserProfileResponseDto> profiles = profileService.fetchAllNormalUsers();
        log.info("Successfully fetched normal user profiles. Total count: {}", profiles.size());
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }


    @Operation(
            summary = "Update normal user profile ",
            description = "REST API to update an existing profile based on id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Http Status NOT FOUND",
                    content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }

    )
    @PutMapping("/normalUserProfiles/{profileId}")
    public ResponseEntity<ResponseDto> updateNormalUserProfile (@Valid @RequestBody CreateNormalUserProfileRequest request, @PathVariable UUID profileId) {
        boolean isUpdated = profileService.updateNormalUserProfile(request,profileId);
        return isUpdated ? new ResponseEntity<>(new ResponseDto(ProfileConstants.STATUS_200, ProfileConstants.MESSAGE_200_UPDATE), HttpStatus.OK) :
                new ResponseEntity<>(new ResponseDto(ProfileConstants.STATUS_417, ProfileConstants.MESSAGE_417_UPDATE), HttpStatus.EXPECTATION_FAILED);
    }

    @Operation(
            summary = "Update dog breeder profile",
            description = "REST API to update an existing profile based on id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Http Status NOT FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }

    )
    @PutMapping("/breederProfiles/{profileId}")
    public ResponseEntity<ResponseDto> updateBreederProfile (@Valid @RequestBody CreateBreederProfileRequest request, @PathVariable UUID profileId) {
        boolean isUpdated = profileService.updateBreederProfile(request, profileId);
        return isUpdated ? new ResponseEntity<>(new ResponseDto(ProfileConstants.STATUS_200, ProfileConstants.MESSAGE_200_UPDATE), HttpStatus.OK) :
                new ResponseEntity<>(new ResponseDto(ProfileConstants.STATUS_417, ProfileConstants.MESSAGE_417_UPDATE), HttpStatus.EXPECTATION_FAILED);
    }

    @Operation(
            summary = "Delete  profile",
            description = "REST API to delete an existing profile based on id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Http Status NOT FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }

    )

    @DeleteMapping("/{profileId}")
    public ResponseEntity<ResponseDto> deleteProfileById (@PathVariable UUID profileId) {
        boolean isDeleted = profileService.deleteProfileById(profileId);
        return isDeleted ? new ResponseEntity<>(new ResponseDto(ProfileConstants.STATUS_200, ProfileConstants.MESSAGE_200_DELETE), HttpStatus.OK) :
                new ResponseEntity<>(new ResponseDto(ProfileConstants.STATUS_417, ProfileConstants.MESSAGE_417_DELETE), HttpStatus.EXPECTATION_FAILED);
    }
}
