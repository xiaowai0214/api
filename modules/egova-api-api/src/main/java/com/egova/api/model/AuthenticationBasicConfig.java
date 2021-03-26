package com.egova.api.model;

import lombok.Data;

@Data
public class AuthenticationBasicConfig {
    /*
     * api key认证的token
     */
    private String key;

    private String username;

    private String password;
}
