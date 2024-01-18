package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.mapper;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses.StockResponseDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.Stock;

public class StockMapper {
    public static StockResponseDto mapToStockResponseDto(Stock stock){
        StockResponseDto stockResponseDto =StockResponseDto.builder().
                        stockId(stock.getStockId()).
                stockName(stock.getStockName()).
                openingPrice(stock.getOpeningPrice()).
                closingPrice(stock.getClosingPrice()).
                highPrice(stock.getHighPrice()).
                lowPrice(stock.getLowPrice()).
                lastPrice(stock.getLastPrice())
                        .build();
        return stockResponseDto;
    }
}
