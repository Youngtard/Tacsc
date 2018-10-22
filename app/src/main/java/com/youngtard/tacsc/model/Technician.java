package com.youngtard.tacsc.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Technician {
    public String fullName;
    public String phoneNumber;

    public Technician() {

    }

    public Technician(String fullName, String phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

}


