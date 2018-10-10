package com.viki.miaosha.service;


import com.viki.miaosha.domain.User;

public interface UserService {
    public User getByid(int id);
    public boolean tx();
}
