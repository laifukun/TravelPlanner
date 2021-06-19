package com.flag.travelplanner.user.service;

import com.flag.travelplanner.user.entity.User;

public interface UserService {
    public boolean register(User user);
    public User getUserById(long userId);
    public boolean login();
}
