package com.bpp_mobile_test.bpp_mobile_test.Model;

import java.io.Serializable;

/**
 * Created by fjesus on 24/05/2018.
 */

public class Usuario  implements Serializable{

    private String email;
    private String password;

    public Usuario(){}

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
}
