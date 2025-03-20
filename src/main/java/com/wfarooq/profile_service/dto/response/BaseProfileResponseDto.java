package com.wfarooq.profile_service.dto.response;

import com.wfarooq.profile_service.constants.ProfileType;

import java.util.UUID;

public abstract class BaseProfileResponseDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String bio;
    private ProfileType profileType;

    public BaseProfileResponseDto(UUID id, String firstName, String lastName, String bio, ProfileType profileType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.profileType = profileType;
    }

    public BaseProfileResponseDto () {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ProfileType getProfileType() {
        return profileType;
    }

    public void setProfileType(ProfileType profileType) {
        this.profileType = profileType;
    }
}
