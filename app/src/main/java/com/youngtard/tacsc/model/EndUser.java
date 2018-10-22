package com.youngtard.tacsc.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class EndUser {

    public String fullName;
    public String phoneNumber;
//    private List<IssueReport>

    public EndUser() {

    }

    public EndUser(String fullName, String phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }
}
