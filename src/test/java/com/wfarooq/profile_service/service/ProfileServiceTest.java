package com.wfarooq.profile_service.service;

import com.wfarooq.profile_service.constants.ProfileType;
import com.wfarooq.profile_service.dto.requests.CreateNormalUserProfileRequest;
import com.wfarooq.profile_service.entity.BaseProfile;
import com.wfarooq.profile_service.repository.BaseProfileRepository;
import com.wfarooq.profile_service.service.impl.ProfileServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    @Mock
    private BaseProfileRepository profileRepository;

    @InjectMocks
    private  ProfileServiceImpl profileService;



    @Test
    @DisplayName(value = "test createBaseProfile")
    void whenGivenValidCreateBaseProfileRequest_ShouldSaveBaseProfileEntity () {

        CreateNormalUserProfileRequest createBaseProfileRequest = new CreateNormalUserProfileRequest(
                "Pathan", "Khan", "I am Pathan Hello", ProfileType.BASE_PROFILE
        );

        profileService.createNormalUserProfile(createBaseProfileRequest);

        verify(profileRepository, times(1)).save(any(BaseProfile.class));

    }
}
