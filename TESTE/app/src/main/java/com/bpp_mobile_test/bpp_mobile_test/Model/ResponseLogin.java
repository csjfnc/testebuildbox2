package com.bpp_mobile_test.bpp_mobile_test.Model;

import java.io.Serializable;

/**
 * Created by fjesus on 25/05/2018.
 */

public class ResponseLogin implements Serializable {

    private String status;
    private String message;
    private String code;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
