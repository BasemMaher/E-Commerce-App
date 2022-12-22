package com.example.app_ecommerce;

import com.example.app_ecommerce.model.Products;

import java.util.List;

public class User {
    String userEmail,userrPassword,userBirthdte;

    public User(String userEmail, String userrPassword, String userBirthdte) {
        this.userEmail = userEmail;
        this.userrPassword = userrPassword;
        this.userBirthdte = userBirthdte;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserrPassword() {
        return userrPassword;
    }

    public void setUserrPassword(String userrPassword) {
        this.userrPassword = userrPassword;
    }

    public String getUserBirthdte() {
        return userBirthdte;
    }

    public void setUserBirthdte(String userBirthdte) {
        this.userBirthdte = userBirthdte;
    }
}
