package com.pbl5.autoattendance.embedded;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityId implements Serializable {
    private String username;
    private String authority;
}

