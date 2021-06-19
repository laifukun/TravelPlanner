package com.flag.travelplanner.user.repository;

import com.flag.travelplanner.user.entity.User;

public interface UserRepository {
    public long count();
    public int create(User user);
    public User findById(long id);
    public User findByUserName(String userName);
    public User findByEmail(String email);
    public int update(User user);
    public int deleteById(long id);
    public int deleteByUserName(String userName);
}
