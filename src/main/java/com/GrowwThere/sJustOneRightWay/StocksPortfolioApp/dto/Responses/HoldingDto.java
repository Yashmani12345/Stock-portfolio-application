package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoldingDto {
    private Long stockId;
    private String stockName;
    private int quantity;
    private Double buyPrice;
    private Double currentPrice;
    private Double gainLoss;
}
