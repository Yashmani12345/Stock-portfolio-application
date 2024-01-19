package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Interface;

import static org.junit.jupiter.api.Assertions.*;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.User;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    @MockBean
    private UserRepository userRepository;
  @BeforeEach
  void setUp() {
    User user=User.builder()
        .userName("Yashmani Singh")
        .userId(2L)
        .build();
    Mockito.when(userRepository.save(user))
        .thenReturn(user);
  }
    @Test
    public void whenUserIsGiven_saveUser()
    {
      User user=User.builder()
          .userName("Yashmani")
          .userId(2L)
          .build();
      User savedUser=userService.saveUser(user);
      assertEquals(user, savedUser);
    }
  }
