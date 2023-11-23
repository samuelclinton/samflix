package com.samflix.backend.utils;

import com.samflix.backend.domain.model.User;

public abstract class UserHelper {

    public static final String USER_ID = "655c1357260db97410f957d2";
    public static final String USERNAME = "user";
    public static final String UPDATED_USERNAME = "new_username";

    public static User generateUser() {
        return User.builder().id(USER_ID).username(USERNAME).build();
    }

}
