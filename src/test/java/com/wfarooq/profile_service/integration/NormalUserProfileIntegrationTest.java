package com.wfarooq.profile_service.integration;

import com.wfarooq.profile_service.constants.ProfileConstants;
import com.wfarooq.profile_service.constants.ProfileType;
import com.wfarooq.profile_service.dto.requests.CreateNormalUserProfileRequest;
import com.wfarooq.profile_service.dto.response.NormalUserProfileResponseDto;
import com.wfarooq.profile_service.dto.response.ResponseDto;
import com.wfarooq.profile_service.entity.NormalUserProfile;
import com.wfarooq.profile_service.repository.BaseProfileRepository;
import com.wfarooq.profile_service.repository.NormalUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NormalUserProfileIntegrationTest {

    @Autowired
    private NormalUserRepository normalUserRepository;
    @Autowired
    private BaseProfileRepository profileRepository;

    @LocalServerPort
    private String port;
    private String baseUrl;


    private static TestRestTemplate testRestTemplate;

    @BeforeAll
    public static void setUp() {

        testRestTemplate = new TestRestTemplate();
    }

    @AfterEach
    public void cleanUp() {
        normalUserRepository.deleteAll();
        profileRepository.deleteAll();
    }



    @Test
    @DisplayName(value = "createNormalUserProfile")
    public void testCreateNormalUserProfile_givenCreateNormalUserProfileRequest_shouldSaveNormalUserProfileToDb () {
        CreateNormalUserProfileRequest createNormalUserProfileRequest = new CreateNormalUserProfileRequest(
                "Pathan", "Khan", "Hello World !", ProfileType.NORMAL_USER_PROFILE
        );
        baseUrl = "http://localhost:" + port + "/api/v1/profiles/normalUserProfile";

        ResponseEntity<ResponseDto> responseEntity = testRestTemplate.postForEntity(
                baseUrl ,
                createNormalUserProfileRequest,
                ResponseDto.class
        );

        ResponseDto responseBody = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseBody.responseStatus());
        assertNotNull(responseBody.responseMessage());
        assertEquals(ProfileConstants.STATUS_201, responseBody.responseStatus());
        assertEquals(ProfileConstants.MESSAGE_201, responseBody.responseMessage());
        assertEquals(1, normalUserRepository.findAll().size());
        assertEquals(1, profileRepository.findAll().size());


    }

    @Test
    @DisplayName(value = "fetch normalUserProfileTest")
    public void testFetchNormalUserProfile_shouldReturnNormalUserResponseDto () {
        NormalUserProfile profile = new NormalUserProfile(
                "John", "Cena", "You can't see me", ProfileType.NORMAL_USER_PROFILE
        );
        normalUserRepository.save(profile);
        UUID profileId = profile.getId();
        baseUrl = "http://localhost:" + port + "/api/v1/profiles/"+profileId;

        ResponseEntity<NormalUserProfileResponseDto> profileResponse = testRestTemplate.getForEntity(baseUrl, NormalUserProfileResponseDto.class);
        NormalUserProfileResponseDto responseBody = profileResponse.getBody();

        assertEquals(HttpStatus.OK ,profileResponse.getStatusCode());
        assertNotNull(responseBody);
        assertEquals(profile.getFirstName(), responseBody.getFirstName());

    }

    @Test
    public void testDeleteProfileById_givenValidProfileId_shouldDeleteProfileFromDb_returnResponseDto () {
        NormalUserProfile profile = new NormalUserProfile(
                "John", "Cena", "You can't see me", ProfileType.NORMAL_USER_PROFILE
        );
        normalUserRepository.save(profile);
        UUID profileId = profile.getId();
        baseUrl = "http://localhost:" + port + "/api/v1/profiles/"+profileId;

        ResponseEntity<ResponseDto> responseEntity = testRestTemplate.exchange(baseUrl, HttpMethod.DELETE, null, ResponseDto.class);
        ResponseDto responseBody = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseBody);
        assertEquals(ProfileConstants.STATUS_200, responseBody.responseStatus());
        assertEquals(ProfileConstants.MESSAGE_200_DELETE, responseBody.responseMessage());

        assertEquals(0, normalUserRepository.findAll().size());
        assertEquals(0, profileRepository.findAll().size());
    }

    @Test
    public void testUpdateProfile_givenValidProfileId_shouldUpdateExistingProfile_returnResponseDto () {
        NormalUserProfile profile = new NormalUserProfile(
                "John", "Cena", "You can't see me", ProfileType.NORMAL_USER_PROFILE
        );
        normalUserRepository.save(profile);
        UUID profileId = profile.getId();
        baseUrl = "http://localhost:" + port + "/api/v1/profiles/normalUserProfiles/"+profileId;

        CreateNormalUserProfileRequest createNormalUserProfileRequest = new CreateNormalUserProfileRequest(
                "Pathan", "Khan", "Hello World !", ProfileType.NORMAL_USER_PROFILE
        );
        ResponseEntity<ResponseDto> responseEntity = testRestTemplate.exchange(baseUrl, HttpMethod.PUT, new HttpEntity<>(createNormalUserProfileRequest), ResponseDto.class);

        ResponseDto responseBody = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseBody);
        assertEquals(ProfileConstants.STATUS_200, responseBody.responseStatus());
        assertEquals(ProfileConstants.MESSAGE_200_UPDATE, responseBody.responseMessage());
    }



}
