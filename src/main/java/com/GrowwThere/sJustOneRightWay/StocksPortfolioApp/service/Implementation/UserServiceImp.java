package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Implementation;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.User;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.repository.UserRepository;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user)
    {
        return userRepository.save(user);
    }
    @Override
    public List<User> fetchAllUsers()
    {
        return userRepository.findAll();
    }
    @Override
    public User fetchUserById(Long userId)
    {
        return userRepository.findById(userId).get();
    }
}
