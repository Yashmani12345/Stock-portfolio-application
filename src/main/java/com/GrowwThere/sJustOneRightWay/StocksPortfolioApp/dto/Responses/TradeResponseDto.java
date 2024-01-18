package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.enums.TradeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeResponseDto {
    private Long userId;
    private Long stockId;
    @Enumerated(EnumType.STRING)
    private TradeType tradeType;
    private int quantity;
}
