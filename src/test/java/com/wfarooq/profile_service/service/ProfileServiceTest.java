package com.wfarooq.profile_service.service;

import com.wfarooq.profile_service.constants.ProfileType;
import com.wfarooq.profile_service.dto.requests.CreateBreederProfileRequest;
import com.wfarooq.profile_service.dto.requests.CreateNormalUserProfileRequest;
import com.wfarooq.profile_service.dto.response.BaseProfileResponseDto;
import com.wfarooq.profile_service.dto.response.BreederProfileResponseDto;
import com.wfarooq.profile_service.dto.response.NormalUserProfileResponseDto;
import com.wfarooq.profile_service.entity.BaseProfile;
import com.wfarooq.profile_service.entity.BreederProfile;
import com.wfarooq.profile_service.entity.NormalUserProfile;
import com.wfarooq.profile_service.exception.ResourceNotFoundException;
import com.wfarooq.profile_service.repository.BaseProfileRepository;
import com.wfarooq.profile_service.repository.BreederProfileRepository;
import com.wfarooq.profile_service.repository.NormalUserRepository;
import com.wfarooq.profile_service.service.impl.ProfileServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    @InjectMocks
    private  ProfileServiceImpl profileService;


    @Mock
    private BaseProfileRepository profileRepository;
    @Mock
    private NormalUserRepository normalUserRepository;
    @Mock
    private BreederProfileRepository breederProfileRepository;



    @Test
    @DisplayName(value = "test createNormalUserProfile")
    void whenGivenValidCreateNormalUserProfileRequest_ShouldSaveNormalUserProfileEntity () {

        CreateNormalUserProfileRequest createNormalUserProfileRequest = new CreateNormalUserProfileRequest(
                "Pathan", "Khan", "Hello World !", ProfileType.NORMAL_USER_PROFILE
        );

        profileService.createNormalUserProfile(createNormalUserProfileRequest);

        verify(normalUserRepository, times(1)).save(any(NormalUserProfile.class));
    }

    @Test
    @DisplayName("test createBreederProfile")
    void whenGivenCreateBreederProfile_ShouldSaveBreederProfileEntity () {
        CreateBreederProfileRequest createBreederProfileRequest = new CreateBreederProfileRequest(
                "Pathan", "Khan", "Hello World !", ProfileType.BREEDER_PROFILE, "Kennal name", "www."
        );

        profileService.createBreederProfile(createBreederProfileRequest);

        verify(breederProfileRepository, times(1)).save(any(BreederProfile.class));
    }

    @Test
    @DisplayName(value = "test fetching profile by id for normal user profile")
    void whenGivenValidProfileIdForNormalUserProfile_ShouldReturnCorrectProfileResponseDto () {
        UUID normalUserProfileId = UUID.randomUUID();
        NormalUserProfile normalUserProfile = new NormalUserProfile("John", "Cena", "Hello john cena", ProfileType.NORMAL_USER_PROFILE);
        normalUserProfile.setId(normalUserProfileId);

        when(profileRepository.findById(normalUserProfileId)).thenReturn(Optional.of(normalUserProfile));

        NormalUserProfileResponseDto responseDto = (NormalUserProfileResponseDto) profileService.fetchProfileById(normalUserProfileId);

        assertNotNull(responseDto);
        assertEquals(normalUserProfile.getFirstName(), responseDto.getFirstName());
        assertEquals(normalUserProfile.getLastName(), responseDto.getLastName());
        assertEquals(normalUserProfile.getBio(), responseDto.getBio());
        assertEquals(normalUserProfile.getProfileType(), responseDto.getProfileType());
        verify(profileRepository).findById(normalUserProfileId);
    }

    @Test
    @DisplayName(value = "test fetching profile by id for breeder profile")
    void whenGivenValidProfileIdForBreederProfile_ShouldReturnCorrectProfileResponseDto () {
        UUID breederProfileId = UUID.randomUUID();
        BreederProfile breederProfile = new BreederProfile("John", "Doe", "Hello john doe", ProfileType.BREEDER_PROFILE, "Kennal", "www.");
        breederProfile.setId(breederProfileId);

        when(profileRepository.findById(breederProfileId)).thenReturn(Optional.of(breederProfile));

        BreederProfileResponseDto responseDto = (BreederProfileResponseDto) profileService.fetchProfileById(breederProfileId);

        assertNotNull(responseDto);
        assertEquals(breederProfile.getFirstName(), responseDto.getFirstName());
        assertEquals(breederProfile.getLastName(), responseDto.getLastName());
        assertEquals(breederProfile.getBio(), responseDto.getBio());
        assertEquals(breederProfile.getProfileType(), responseDto.getProfileType());
        assertEquals(breederProfile.getKennelName(), responseDto.getKennelName());
        assertEquals(breederProfile.getWebsite(), responseDto.getWebsite());
        verify(profileRepository).findById(breederProfileId);

    }

    @Test
    @DisplayName(value = "test fetching all profiles")
    void whenFetchingAllProfiles_shouldReturnListOfCorrectResponseDtos () {
        UUID breederProfileId = UUID.randomUUID();
        BreederProfile breederProfile = new BreederProfile("John", "Doe", "Hello john doe", ProfileType.BREEDER_PROFILE, "Kennal", "www.");
        breederProfile.setId(breederProfileId);

        UUID normalUserProfileId = UUID.randomUUID();
        NormalUserProfile normalUserProfile = new NormalUserProfile("John", "Cena", "Hello john cena", ProfileType.NORMAL_USER_PROFILE);
        normalUserProfile.setId(normalUserProfileId);

        List<BaseProfile> profiles = new ArrayList<>();
        profiles.add(normalUserProfile);
        profiles.add(breederProfile);

        when(profileRepository.findAll()).thenReturn(profiles);

        List<BaseProfileResponseDto> responseDto = profileService.fetchAllProfiles();

        assertEquals(2, responseDto.size());
        assertEquals(responseDto.get(0).getFirstName(), normalUserProfile.getFirstName());
        assertInstanceOf(NormalUserProfileResponseDto.class, responseDto.get(0));
        assertInstanceOf(BreederProfileResponseDto.class, responseDto.get(1));

        verify(profileRepository, times(1)).findAll();
    }

    @Test
    @DisplayName(value = "test for fetching all normal user profiles")
    void whenFetchingAllNormalUserProfiles_shouldReturnListOfNormalUserProfileResponseDtos () {
        UUID normalUserProfileIdOne = UUID.randomUUID();
        NormalUserProfile normalUserProfile = new NormalUserProfile("John", "Cena", "Hello john cena", ProfileType.NORMAL_USER_PROFILE);
        normalUserProfile.setId(normalUserProfileIdOne);

        UUID normalUserProfileIdTwo = UUID.randomUUID();
        NormalUserProfile normalUserProfileTwo = new NormalUserProfile("John", "Cena", "Hello john cena", ProfileType.NORMAL_USER_PROFILE);
        normalUserProfileTwo.setId(normalUserProfileIdTwo);

        UUID normalUserProfileIdThree = UUID.randomUUID();
        NormalUserProfile normalUserProfileThree = new NormalUserProfile("John", "Cena", "Hello john cena", ProfileType.NORMAL_USER_PROFILE);
        normalUserProfileThree.setId(normalUserProfileIdThree);

        List<NormalUserProfile> savedProfiles = new ArrayList<>();
        savedProfiles.add(normalUserProfile);
        savedProfiles.add(normalUserProfileTwo);
        savedProfiles.add(normalUserProfileThree);

        when(normalUserRepository.findAll()).thenReturn(savedProfiles);

        List<NormalUserProfileResponseDto> allNormalUserProfiles = profileService.fetchAllNormalUsers();

        assertNotNull(allNormalUserProfiles);
        assertEquals(3, allNormalUserProfiles.size());
        assertInstanceOf(NormalUserProfileResponseDto.class, allNormalUserProfiles.get(0));
        assertInstanceOf(NormalUserProfileResponseDto.class, allNormalUserProfiles.get(1));
        assertInstanceOf(NormalUserProfileResponseDto.class, allNormalUserProfiles.get(2));
        assertEquals(allNormalUserProfiles.get(0).getFirstName(), normalUserProfile.getFirstName());
        assertEquals(allNormalUserProfiles.get(1).getFirstName(), normalUserProfileTwo.getFirstName());
        assertEquals(allNormalUserProfiles.get(2).getFirstName(), normalUserProfileThree.getFirstName());

        verify(normalUserRepository, times(1)).findAll();

    }

    @Test
    @DisplayName(value = "test for fetching all breeder profiles")
    void whenFetchingAllBreederProfiles_shouldReturnListOfBreederProfileResponseDtos () {
        UUID breederProfileId = UUID.randomUUID();
        BreederProfile breederProfile = new BreederProfile("John", "Doe", "Hello john doe", ProfileType.BREEDER_PROFILE, "Kennal", "www.");
        breederProfile.setId(breederProfileId);

        UUID breederProfileIdTwo = UUID.randomUUID();
        BreederProfile breederProfileTwo = new BreederProfile("John", "Doe", "Hello john doe", ProfileType.BREEDER_PROFILE, "Kennal", "www.");
        breederProfileTwo.setId(breederProfileIdTwo);

        UUID breederProfileIdThree = UUID.randomUUID();
        BreederProfile breederProfileThree = new BreederProfile("John", "Doe", "Hello john doe", ProfileType.BREEDER_PROFILE, "Kennal", "www.");
        breederProfileThree.setId(breederProfileIdThree);

        List<BreederProfile> savedProfiles = new ArrayList<>();
        savedProfiles.add(breederProfile);
        savedProfiles.add(breederProfileTwo);
        savedProfiles.add(breederProfileThree);

        when(breederProfileRepository.findAll()).thenReturn(savedProfiles);

        List<BreederProfileResponseDto> allBreederProfiles = profileService.fetchAllBreeders();

        assertNotNull(allBreederProfiles);
        assertEquals(3, allBreederProfiles.size());
        assertInstanceOf(BreederProfileResponseDto.class, allBreederProfiles.get(0));
        assertEquals(breederProfile.getProfileType(), allBreederProfiles.get(0).getProfileType());

        verify(breederProfileRepository, times(1)).findAll();


    }

    @Test
    @DisplayName(value = "test for non existing profile id")
    void whenProfileIdNotExist_shouldThrowResourceNotFoundException () {
        UUID invalidId = UUID.randomUUID();

        when(profileRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            profileService.fetchProfileById(invalidId);
        });

        verify(profileRepository).findById(invalidId);
    }

    // chatgpt written tests
    @Test
    @DisplayName("updateNormalUserProfile - valid")
    void whenUpdatingExistingNormalUserProfile_shouldUpdateAndReturnTrue() {
        UUID profileId = UUID.randomUUID();
        CreateNormalUserProfileRequest request = new CreateNormalUserProfileRequest("John", "Cena", "Bio", ProfileType.NORMAL_USER_PROFILE);
        NormalUserProfile existingProfile = new NormalUserProfile("Old", "Name", "Old Bio", ProfileType.NORMAL_USER_PROFILE);
        existingProfile.setId(profileId);

        when(normalUserRepository.findById(profileId)).thenReturn(Optional.of(existingProfile));

        boolean result = profileService.updateNormalUserProfile(request, profileId);

        assertTrue(result);
        verify(normalUserRepository).findById(profileId);
        verify(normalUserRepository).save(existingProfile);
    }

    @Test
    @DisplayName("updateNormalUserProfile - profile not found")
    void whenUpdatingNonExistentNormalUserProfile_shouldThrowResourceNotFound() {
        UUID invalidId = UUID.randomUUID();
        CreateNormalUserProfileRequest request = new CreateNormalUserProfileRequest("John", "Cena", "Bio", ProfileType.NORMAL_USER_PROFILE);

        when(normalUserRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                profileService.updateNormalUserProfile(request, invalidId));
    }

    @Test
    @DisplayName("updateBreederProfile - valid")
    void whenUpdatingExistingBreederProfile_shouldUpdateAndReturnTrue() {
        UUID profileId = UUID.randomUUID();
        CreateBreederProfileRequest request = new CreateBreederProfileRequest("Jane", "Doe", "Bio", ProfileType.BREEDER_PROFILE, "Kennel", "www");
        BreederProfile existingProfile = new BreederProfile("Old", "Breeder", "Old Bio", ProfileType.BREEDER_PROFILE, "OldKennel", "old.com");
        existingProfile.setId(profileId);

        when(breederProfileRepository.findById(profileId)).thenReturn(Optional.of(existingProfile));

        boolean result = profileService.updateBreederProfile(request, profileId);

        assertTrue(result);
        verify(breederProfileRepository).findById(profileId);
        verify(breederProfileRepository).save(existingProfile);
    }

    @Test
    @DisplayName("updateBreederProfile - profile not found")
    void whenUpdatingNonExistentBreederProfile_shouldThrowResourceNotFound() {
        UUID invalidId = UUID.randomUUID();
        CreateBreederProfileRequest request = new CreateBreederProfileRequest("Jane", "Doe", "Bio", ProfileType.BREEDER_PROFILE, "Kennel", "www");

        when(breederProfileRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                profileService.updateBreederProfile(request, invalidId));
    }

    @Test
    @DisplayName("deleteProfileById - valid")
    void whenDeletingExistingProfile_shouldDeleteAndReturnTrue() {
        UUID profileId = UUID.randomUUID();
        BaseProfile profile = mock(BaseProfile.class);

        when(profileRepository.findById(profileId)).thenReturn(Optional.of(profile));

        boolean result = profileService.deleteProfileById(profileId);

        assertTrue(result);
        verify(profileRepository).findById(profileId);
        verify(profileRepository).delete(profile);
    }

    @Test
    @DisplayName("deleteProfileById - profile not found")
    void whenDeletingNonExistentProfile_shouldThrowResourceNotFound() {
        UUID invalidId = UUID.randomUUID();

        when(profileRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                profileService.deleteProfileById(invalidId));
    }



}
