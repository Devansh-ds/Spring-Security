package com.sadds.jwtpractice.User;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.hibernate.type.TrueFalseConverter;

@Entity
@Table(name = "practice_token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;

    @Convert(converter = TrueFalseConverter.class)
    private boolean expired;

    @Convert(converter = TrueFalseConverter.class)
    private boolean revoked;

    @Enumerated(EnumType.STRING)
    private TokenType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Token() {}

    public Token(String token, boolean expired, boolean revoked, TokenType type, User user) {
        this.token = token;
        this.expired = expired;
        this.revoked = revoked;
        this.type = type;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
