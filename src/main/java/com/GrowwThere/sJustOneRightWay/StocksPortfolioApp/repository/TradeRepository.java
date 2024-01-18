package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.repository;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.enums.TradeType;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade,Long> {
    List<Trade> findByUserIdAndStockIdAndTradeType(Long userId, Long stockId, TradeType tradeType);

    List<Trade> findByUserIdAndTradeType(Long userId, TradeType tradeType);
}

