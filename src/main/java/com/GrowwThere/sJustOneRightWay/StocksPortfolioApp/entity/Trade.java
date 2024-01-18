package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.enums.TradeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tradeId;
    @Enumerated(EnumType.STRING)
    private TradeType tradeType;
    private Double price;
    private int quantity;
    private Long userId;
    private Long stockId;
}
