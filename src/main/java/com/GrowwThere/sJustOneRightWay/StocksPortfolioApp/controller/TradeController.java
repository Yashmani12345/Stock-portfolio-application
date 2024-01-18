package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.controller;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Requets.TradeRequestDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses.TradeResponseDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.error.InsufficientStockException;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.error.StockNotFoundException;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.error.UserNotFoundException;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Interface.TradeService;
import jakarta.validation.Valid;
import javax.naming.InsufficientResourcesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class TradeController {
    @Autowired
    private TradeService tradeService;
    @PostMapping("/user-trade")
    public TradeResponseDto createUserTrade(@Valid @RequestBody TradeRequestDto tradeRequestDto)
        throws StockNotFoundException, UserNotFoundException, InsufficientResourcesException, InsufficientStockException {
        return tradeService.createUserTrade(tradeRequestDto);
    }
}
