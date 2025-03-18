package com.wfarooq.profile_service.service;

import com.wfarooq.profile_service.constants.ProfileType;
import com.wfarooq.profile_service.dto.requests.CreateBaseProfileRequest;
import com.wfarooq.profile_service.entity.BaseProfile;
import com.wfarooq.profile_service.repository.ProfileRepository;
import com.wfarooq.profile_service.service.impl.ProfileServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    @Mock
    private  ProfileRepository profileRepository;

    @InjectMocks
    private  ProfileServiceImpl profileService;



    @Test
    @DisplayName(value = "test createBaseProfile")
    void whenGivenValidCreateBaseProfileRequest_ShouldSaveBaseProfileEntity () {

        CreateBaseProfileRequest createBaseProfileRequest = new CreateBaseProfileRequest(
                "Pathan", "Khan", "I am Pathan Hello", ProfileType.BASE_PROFILE
        );

        profileService.createBaseProfile(createBaseProfileRequest);

        verify(profileRepository, times(1)).save(any(BaseProfile.class));

    }
}
