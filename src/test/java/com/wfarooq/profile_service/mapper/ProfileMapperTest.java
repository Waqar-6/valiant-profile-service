package com.wfarooq.profile_service.mapper;

import com.wfarooq.profile_service.constants.ProfileType;
import com.wfarooq.profile_service.dto.requests.CreateBreederProfileRequest;
import com.wfarooq.profile_service.dto.requests.CreateNormalUserProfileRequest;
import com.wfarooq.profile_service.entity.BreederProfile;
import com.wfarooq.profile_service.entity.NormalUserProfile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfileMapperTest {

    @Test
    @DisplayName(value = "test mapToBaseProfile")
    void whenGivenCreateNormalUserProfileRequest_ShouldReturnNormalUserProfileEntity () {
        UUID id = UUID.randomUUID();
        CreateNormalUserProfileRequest createNormalUserProfileRequest = new CreateNormalUserProfileRequest(
                "Paddy", "Khan", "Hello world !", ProfileType.NORMAL_USER_PROFILE
        );

        NormalUserProfile normalUserProfile = new NormalUserProfile();
        normalUserProfile.setId(id);

        ProfileMapper.mapToNormalUserProfile(createNormalUserProfileRequest, normalUserProfile);

        assertEquals(normalUserProfile.getId(), id);
        assertEquals(normalUserProfile.getFirstName(), createNormalUserProfileRequest.getFirstName());
        assertEquals(normalUserProfile.getLastName(), createNormalUserProfileRequest.getLastName());
        assertEquals(normalUserProfile.getProfileType(), createNormalUserProfileRequest.getProfileType());
    }

    @Test
    @DisplayName("test map create breeder profile to breeder profile entity")
    void whenGivenCreateBreederProfileRequest_ShouldReturnBreederProfileEntity () {
        UUID id = UUID.randomUUID();
        CreateBreederProfileRequest createBreederProfileRequest = new CreateBreederProfileRequest(
                "Paddy", "Khan", "Hello world !", ProfileType.BREEDER_PROFILE, "Vom hause", "www."
        );

        BreederProfile breederProfile = new BreederProfile();
        breederProfile.setId(id);

        ProfileMapper.mapToBreederProfile(createBreederProfileRequest,breederProfile);

        assertEquals(breederProfile.getId(), id);
        assertEquals(breederProfile.getFirstName(), createBreederProfileRequest.getFirstName());
        assertEquals(breederProfile.getLastName(), createBreederProfileRequest.getLastName());
        assertEquals(breederProfile.getProfileType(), createBreederProfileRequest.getProfileType());
        assertEquals(breederProfile.getKennelName(), createBreederProfileRequest.getKennelName());
        assertEquals(breederProfile.getWebsite(), createBreederProfileRequest.getWebsite());

    }
}
