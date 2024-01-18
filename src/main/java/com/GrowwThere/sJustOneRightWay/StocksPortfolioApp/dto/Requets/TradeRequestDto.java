package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Requets;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.enums.TradeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeRequestDto {
   // @NotBlank(message="Please add user Id.")
    private Long userId;
   // @NotBlank(message="Please add stock Id.")
    private Long stockId;
   // @NotBlank(message="Please add trade type.")
    @Enumerated(EnumType.STRING)
    private TradeType tradeType;
   // @NotBlank(message="Please add quantity.")
    private int quantity;
}
//read about notBlank, notNull and not notEmpty
//command+option+arrowKey