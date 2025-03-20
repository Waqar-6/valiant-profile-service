package com.wfarooq.profile_service.dto.response;

import com.wfarooq.profile_service.constants.ProfileType;

import java.util.UUID;

public class BreederProfileResponseDto extends BaseProfileResponseDto{

    private String kennelName;
    private String website;

    public BreederProfileResponseDto(UUID id, String firstName, String lastName, String bio, ProfileType profileType, String kennelName, String website) {
        super(id, firstName, lastName, bio, profileType);
        this.kennelName = kennelName;
        this.website = website;
    }

    public BreederProfileResponseDto(String kennelName, String website) {
        this.kennelName = kennelName;
        this.website = website;
    }

    public BreederProfileResponseDto () {}

    public String getKennelName() {
        return kennelName;
    }

    public void setKennelName(String kennelName) {
        this.kennelName = kennelName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
