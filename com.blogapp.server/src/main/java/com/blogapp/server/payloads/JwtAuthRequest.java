package com.blogapp.server.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String username; //email
    private String password;
}
