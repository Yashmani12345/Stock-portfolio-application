package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//Todo: give table name (unique names/constants)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stockId;
    @NotBlank(message="Please add trade type.")
    private String stockName;
//    @NotBlank(message = "Please add trade type.")
    private double openingPrice;
//    @NotBlank(message="Please add trade type.")
    private double closingPrice;
//    @NotBlank(message="Please add trade type.")
    private double highPrice;
//    @NotBlank(message="Please add trade type.")
    private double lowPrice;
//    @NotBlank(message="Please add trade type.")
    private double lastPrice;
}
