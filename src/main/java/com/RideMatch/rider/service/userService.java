package com.RideMatch.rider.service;

import com.RideMatch.rider.entity.User;

public interface userService {
    User register(User user);

    String login(User user);
}
