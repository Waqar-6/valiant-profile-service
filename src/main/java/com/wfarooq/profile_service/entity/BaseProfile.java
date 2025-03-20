package com.wfarooq.profile_service.entity;

import com.wfarooq.profile_service.constants.ProfileType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseProfile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String bio;
    @Enumerated(value = EnumType.STRING)
    private ProfileType profileType;

    public BaseProfile(String firstName, String lastName, String bio, ProfileType profileType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.profileType = profileType;
    }

    public BaseProfile () {}

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

    @Override
    public String toString() {
        return "BaseProfile{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bio='" + bio + '\'' +
                ", profileType=" + profileType +
                '}';
    }
}
