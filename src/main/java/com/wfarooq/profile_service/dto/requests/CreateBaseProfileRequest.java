package com.wfarooq.profile_service.dto.requests;

import com.wfarooq.profile_service.constants.ProfileType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateBaseProfileRequest {


    @NotBlank(message = "first name can not be empty")
    @Size(min = 3, max = 20, message = "first name can not be less then 3 characters and not more then 20 ")
    String firstName;
    @NotBlank(message = "last name can not be empty")
    @Size(min = 3, max = 20, message = "last name can not be less then 3 characters and not more then 20 ")
    String lastName;
    @NotBlank(message = "bio name can not be empty")
    @Size(min = 10, max = 300, message = "bio can not be less then 10 characters and not more then 300 ")
    String bio;
    @NotNull(message = "profile type can not be empty")
    ProfileType profileType;

    public CreateBaseProfileRequest(String firstName, String lastName, String bio, ProfileType profileType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.profileType = profileType;
    }

    public CreateBaseProfileRequest() {}

    public @NotBlank(message = "first name can not be empty") @Size(min = 3, max = 20, message = "first name can not be less then 3 characters and not more then 20 ") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "first name can not be empty") @Size(min = 3, max = 20, message = "first name can not be less then 3 characters and not more then 20 ") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "last name can not be empty") @Size(min = 3, max = 20, message = "last name can not be less then 3 characters and not more then 20 ") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "last name can not be empty") @Size(min = 3, max = 20, message = "last name can not be less then 3 characters and not more then 20 ") String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = "bio name can not be empty") @Size(min = 10, max = 300, message = "bio can not be less then 10 characters and not more then 300 ") String getBio() {
        return bio;
    }

    public void setBio(@NotBlank(message = "bio name can not be empty") @Size(min = 10, max = 300, message = "bio can not be less then 10 characters and not more then 300 ") String bio) {
        this.bio = bio;
    }

    public @NotNull(message = "profile type can not be empty") ProfileType getProfileType() {
        return profileType;
    }

    public void setProfileType(@NotNull(message = "profile type can not be empty") ProfileType profileType) {
        this.profileType = profileType;
    }
}
