package com.demo.aevicedemo.repositories;

import com.demo.aevicedemo.models.User;


public class UserRepository {

    public  UserRepository() {}

    public User getUser() {
        return new User("Michel Hell", 30, "B+", "https://i0.wp.com/365webresources.com/wp-content/uploads/2016/09/FREE-PROFILE-AVATARS.png?resize=502%2C494&ssl=1");
    }
}
