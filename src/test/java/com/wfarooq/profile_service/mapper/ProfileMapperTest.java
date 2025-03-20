package com.wfarooq.profile_service.mapper;

import com.wfarooq.profile_service.constants.ProfileType;
import com.wfarooq.profile_service.dto.requests.CreateNormalUserProfileRequest;
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
                "Pathan", "Khan", "I am Pathan Hello", ProfileType.NORMAL_USER_PROFILE
        );

        NormalUserProfile normalUserProfile = new NormalUserProfile();
        normalUserProfile.setId(id);

        ProfileMapper.mapToNormalUserProfile(createNormalUserProfileRequest, normalUserProfile);

        assertEquals(normalUserProfile.getId(), id);
        assertEquals(normalUserProfile.getFirstName(), createNormalUserProfileRequest.getFirstName());
        assertEquals(normalUserProfile.getLastName(), createNormalUserProfileRequest.getLastName());
        assertEquals(normalUserProfile.getProfileType(), createNormalUserProfileRequest.getProfileType());
    }
}
