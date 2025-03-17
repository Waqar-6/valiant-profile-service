package com.wfarooq.profile_service.entity;

import com.wfarooq.profile_service.constants.ProfileType;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
@Entity
@PrimaryKeyJoinColumn
public class BreederProfile extends BaseProfile{
    private String kennelName;
    private String website;

    public BreederProfile(String firstName, String lastName, String bio, ProfileType profileType, String kennelName, String website) {
        super(firstName, lastName, bio, profileType);
        this.kennelName = kennelName;
        this.website = website;
    }

    public BreederProfile(String kennelName, String website) {
        this.kennelName = kennelName;
        this.website = website;
    }

    public BreederProfile () {}

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

    @Override
    public String toString() {
        return "BreederProfile{" +
                "kennelName='" + kennelName + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
