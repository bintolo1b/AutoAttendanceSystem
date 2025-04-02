package com.pbl5.autoattendance.model;

import com.pbl5.autoattendance.embedded.AuthorityId;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authorities")
@NoArgsConstructor
public class Authority {
    @EmbeddedId
    private AuthorityId id;

    @MapsId("username")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    public AuthorityId getId() {
        return id;
    }

    public void setId(AuthorityId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
