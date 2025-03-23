package com.wfarooq.profile_service.integration;

import com.wfarooq.profile_service.constants.ProfileConstants;
import com.wfarooq.profile_service.constants.ProfileType;
import com.wfarooq.profile_service.dto.requests.CreateBreederProfileRequest;
import com.wfarooq.profile_service.dto.response.BreederProfileResponseDto;
import com.wfarooq.profile_service.dto.response.ResponseDto;
import com.wfarooq.profile_service.entity.BreederProfile;
import com.wfarooq.profile_service.repository.BaseProfileRepository;
import com.wfarooq.profile_service.repository.BreederProfileRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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
public class BreederProfileIntegrationTest {

    @Autowired
    private BreederProfileRepository breederProfileRepository;
    @Autowired
    private BaseProfileRepository profileRepository;

    @LocalServerPort
    private String port;

    private String baseUrl;

    private static TestRestTemplate testRestTemplate;

    @BeforeAll
    public static void setUp() {testRestTemplate = new TestRestTemplate();}


    @AfterEach
    public void cleanUp () {
        profileRepository.deleteAll();
        breederProfileRepository.deleteAll();
    }

    @Test
    public void testCreateBreederProfile_givenValidCreateBreederProfileRequest_shouldSaveToDbAndReturnResponseDto () {
        CreateBreederProfileRequest createBreederProfileRequest = new CreateBreederProfileRequest(
                "Pathan", "Khan", "Hello World !", ProfileType.BREEDER_PROFILE, "Kennal name", "www."
        );

        baseUrl = "http://localhost:" + port + "/api/v1/profiles/breederProfile";
        ResponseEntity<ResponseDto> responseEntity = testRestTemplate.postForEntity(baseUrl, createBreederProfileRequest, ResponseDto.class);
        ResponseDto responseBody = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseBody);
        assertEquals(ProfileConstants.STATUS_201,responseBody.responseStatus());
        assertEquals(ProfileConstants.MESSAGE_201, responseBody.responseMessage());
    }

    // chatgpt written but ive read and understood given a pass
    @Test
    public void testFetchBreederProfile_givenValidProfileId_shouldReturnBreederProfileDto() {
        BreederProfile profile = new BreederProfile(
                "John", "Doe", "Experienced breeder", ProfileType.BREEDER_PROFILE, "Vom Hause", "www.vomhause.com"
        );
        breederProfileRepository.save(profile);
        UUID profileId = profile.getId();
        baseUrl = "http://localhost:" + port + "/api/v1/profiles/" + profileId;

        ResponseEntity<BreederProfileResponseDto> response =
                testRestTemplate.getForEntity(baseUrl, BreederProfileResponseDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(profile.getFirstName(), response.getBody().getFirstName());
    }

    @Test
    public void testUpdateBreederProfile_givenValidId_shouldUpdateAndReturnResponseDto() {
        BreederProfile profile = new BreederProfile(
                "John", "Doe", "Old Bio", ProfileType.BREEDER_PROFILE, "Old Kennel", "oldsite.com"
        );
        breederProfileRepository.save(profile);
        UUID profileId = profile.getId();

        CreateBreederProfileRequest updateRequest = new CreateBreederProfileRequest(
                "Updated", "Breeder", "New is cool Bio", ProfileType.BREEDER_PROFILE, "New Kennel", "newsite.com"
        );

        baseUrl = "http://localhost:" + port + "/api/v1/profiles/breederProfiles/" + profileId;

        ResponseEntity<ResponseDto> response = testRestTemplate.exchange(
                baseUrl,
                HttpMethod.PUT,
                new HttpEntity<>(updateRequest),
                ResponseDto.class
        );


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ProfileConstants.STATUS_200, response.getBody().responseStatus());
        assertEquals(ProfileConstants.MESSAGE_200_UPDATE, response.getBody().responseMessage());
    }

    @Test
    public void testDeleteBreederProfile_givenValidId_shouldDeleteAndReturnResponseDto() {
        BreederProfile profile = new BreederProfile(
                "Delete", "Me", "Bio", ProfileType.BREEDER_PROFILE, "Kennel", "www.delete.com"
        );
        breederProfileRepository.save(profile);
        UUID profileId = profile.getId();

        baseUrl = "http://localhost:" + port + "/api/v1/profiles/" + profileId;

        ResponseEntity<ResponseDto> response = testRestTemplate.exchange(
                baseUrl,
                HttpMethod.DELETE,
                null,
                ResponseDto.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ProfileConstants.STATUS_200, response.getBody().responseStatus());
        assertEquals(ProfileConstants.MESSAGE_200_DELETE, response.getBody().responseMessage());
    }



}
