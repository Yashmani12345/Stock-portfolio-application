package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.repository;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
