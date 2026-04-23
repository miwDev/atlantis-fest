package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.entity.User;

public interface UserService {
    User findByUsername(String username);
}
