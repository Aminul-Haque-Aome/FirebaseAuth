package com.fendonus.fake_coder.firebaseauth.models;

import com.google.gson.annotations.SerializedName;

public class UserInfoModel {

    @SerializedName("user_name")
    private String name;

    @SerializedName("user_unique_id")
    private String userUniqueId;

    @SerializedName("user_national_id")
    private String nationalId;

    @SerializedName("user_address")
    private String address;

    @SerializedName("user_phone_number")
    private String phoneNumber;

    @SerializedName("user_email")
    private String email;

    @SerializedName("user_password")
    private String password;

    public UserInfoModel() {}

    public UserInfoModel(String name, String userUniqueId, String nationalId, String address, String email, String password, String phoneNumber) {
        this.name = name;
        this.userUniqueId = userUniqueId;
        this.nationalId = nationalId;
        this.address = address;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserUniqueId() {
        return userUniqueId;
    }

    public void setUserUniqueId(String userUniqueId) {
        this.userUniqueId = userUniqueId;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
