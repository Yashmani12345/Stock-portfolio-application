package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoldingDto {
    private Long StockId;
    private String StackName;
    private int quantity;
    private Double BuyPrice;
    private Double CurrentPrice;
    private Double GainLoss;
}
