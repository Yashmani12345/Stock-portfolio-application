package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioResponseDto {
    private List<HoldingDto> holdings=new ArrayList<>();
    private int totalPortfolioHoldings;
    private double totalBuyPrice;
    private Float totalGainLossPercentage;
}
