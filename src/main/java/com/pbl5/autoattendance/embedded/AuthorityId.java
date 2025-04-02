package com.pbl5.autoattendance.embedded;

import java.io.Serializable;

public class AuthorityId implements Serializable {
    private String username;
    private String authority;

    public AuthorityId() {
    }

    public AuthorityId(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public String getAuthority() {
        return authority;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}

