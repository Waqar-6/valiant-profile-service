package com.wfarooq.profile_service.dto.requests;

import com.wfarooq.profile_service.constants.ProfileType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateBaseProfileRequest(
        @NotBlank(message = "first name can not be empty")
                @Size(min = 3, max = 20, message = "first name can not be less then 3 characters and not more then 20 ")
        String firstName,
        @NotBlank(message = "last name can not be empty")
        @Size(min = 3, max = 20, message = "last name can not be less then 3 characters and not more then 20 ")
        String lastName,
        @NotBlank(message = "bio name can not be empty")
        @Size(min = 10, max = 300, message = "bio can not be less then 10 characters and not more then 300 ")
        String bio,
        @NotNull(message = "profile type can not be empty")
        ProfileType profileType) {
}
