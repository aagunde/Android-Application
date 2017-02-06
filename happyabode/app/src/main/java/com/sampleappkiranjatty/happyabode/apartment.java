package com.sampleappkiranjatty.happyabode;

/**
 * Created by kiranjatty on 12/2/16.
 */
public class apartment {
    String apartment, username, password, email;

    public void set_username(String username) {
        this.username = username;
    }

    public void set_apartment(String apartment) {
        this.apartment = apartment;
    }
    public void set_email(String email) {
        this.email = email;
    }

    public void set_password(String password) {
        this.password = password;
    }

    public String get_apartment() {
        return apartment;
    }

    public String get_username() {
        return username;
    }

    public String get_password() { return password; }
    public String get_email() { return email; }
}
