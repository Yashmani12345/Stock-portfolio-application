package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.repository;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {
}
