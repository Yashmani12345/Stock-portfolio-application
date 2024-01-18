package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Interface;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> fetchAllUsers();

    User fetchUserById(Long userId);
}
