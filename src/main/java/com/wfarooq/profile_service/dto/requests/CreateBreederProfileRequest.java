package com.wfarooq.profile_service.dto.requests;

import com.wfarooq.profile_service.constants.ProfileType;

public class CreateBreederProfileRequest extends CreateBaseProfileRequest {
    private String kennelName;
    private String website;

    public CreateBreederProfileRequest(String firstName, String lastName, String bio, ProfileType profileType, String kennelName, String website) {
        super(firstName, lastName, bio, profileType);
        this.kennelName = kennelName;
        this.website = website;
    }

    public CreateBreederProfileRequest(String kennelName, String website) {
        this.kennelName = kennelName;
        this.website = website;
    }

    public CreateBreederProfileRequest () {}

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
