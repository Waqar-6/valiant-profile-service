package com.wfarooq.profile_service.mapper;

import com.wfarooq.profile_service.constants.ProfileType;
import com.wfarooq.profile_service.dto.requests.CreateBaseProfileRequest;
import com.wfarooq.profile_service.entity.BaseProfile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.UUID;

public class ProfileMapperTest {

    @Test
    @DisplayName(value = "test mapToBaseProfile")
    void whenGivenCreateBaseProfileRequest_ShouldReturnBaseProfileEntity () {
        UUID id = UUID.randomUUID();
        CreateBaseProfileRequest createBaseProfileRequest = new CreateBaseProfileRequest(
                "Pathan", "Khan", "I am Pathan Hello", ProfileType.BASE_PROFILE
        );

        BaseProfile baseProfile = new BaseProfile();
        baseProfile.setId(id);

        ProfileMapper.mapToBaseProfile(createBaseProfileRequest, baseProfile);

        assertEquals(baseProfile.getId(), id);
        assertEquals(baseProfile.getFirstName(), createBaseProfileRequest.firstName());
        assertEquals(baseProfile.getLastName(), createBaseProfileRequest.lastName());
        assertEquals(baseProfile.getProfileType(), createBaseProfileRequest.profileType());

    }
}
