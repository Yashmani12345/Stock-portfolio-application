package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Interface;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Requets.TradeRequestDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses.TradeResponseDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.Trade;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.error.InsufficientStockException;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.error.StockNotFoundException;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.error.UserNotFoundException;
import javax.naming.InsufficientResourcesException;

public interface TradeService {
    TradeResponseDto createUserTrade(TradeRequestDto tradeRequestDto)
        throws StockNotFoundException, UserNotFoundException, InsufficientResourcesException, InsufficientStockException;
}
