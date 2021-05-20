package com.example.travelholic.model;

public class Member {

    private final String avatar;
    private final String username;

    public Member(String avatar, String username) {
        this.avatar = avatar;
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUsername() {
        return username;
    }
}
