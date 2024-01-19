package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockResponseDto {
    private Long stockId;
    private String stockName;
    private double openingPrice;
    private double closingPrice;
    private double highPrice;
    private double lowPrice;
    private double lastPrice;
}
