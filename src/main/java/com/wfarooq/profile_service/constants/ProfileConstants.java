package com.wfarooq.profile_service.constants;

public final class ProfileConstants {

    private ProfileConstants () {}

    // Status Codes
    public static final String STATUS_200 = "200";
    public static final String STATUS_201 = "201";
    public static final String STATUS_417 = "417";
    public static final String STATUS_500 = "500";

    // Messages
    public static final String MESSAGE_200 = "Request processed successfully.";
    public static final String MESSAGE_201 = "Profile created successfully.";
    public static final String MESSAGE_200_UPDATE = "Profile updated successfully.";
    public static final String MESSAGE_200_DELETE = "Profile deleted successfully.";
    public static final String MESSAGE_417_UPDATE = "Expectation failed during update. Please check your input.";
    public static final String MESSAGE_417_DELETE = "Expectation failed during deletion. Please check your input.";
    public static final String MESSAGE_500 = "Internal server error. Please try again later.";
}
