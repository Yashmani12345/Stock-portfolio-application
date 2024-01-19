package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Implementation;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses.HoldingDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses.PortfolioResponseDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.Trade;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.enums.TradeType;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.repository.StockRepository;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.repository.TradeRepository;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Interface.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PortfolioServiceImp implements PortfolioService {

    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    StockRepository stockRepository;

    public PortfolioResponseDto fetchUserPortfolioById(Long userId) {

        List<Trade> userBuyTrades = tradeRepository.findByUserIdAndTradeType(userId, TradeType.Buy);
        List<Trade> userSellTrades = tradeRepository.findByUserIdAndTradeType(userId,
            TradeType.Sell);

        Map<Long, List<Trade>> buyTradesOfUser = tradesOfOneStockIdOfUser(userBuyTrades, userId,
            TradeType.Buy);
        Map<Long, List<Trade>> sellTradesOfUser = tradesOfOneStockIdOfUser(userSellTrades, userId,
            TradeType.Sell);

        List<HoldingDto> holdings=calculationOfHolding(buyTradesOfUser,sellTradesOfUser);
        return calculationOfPortfolio(holdings);
    }

    private Map<Long, List<Trade>> tradesOfOneStockIdOfUser(List<Trade> userTrades, Long userId,
        TradeType tradeType) {
        Map<Long, Integer> stockIdMap = new HashMap<>();
        Map<Long, List<Trade>> TradesOfUserCorrespondToStockId = new HashMap<>();
        for (Trade trade : userTrades) {
            if (!stockIdMap.containsKey(trade.getStockId())) {
                stockIdMap.put(trade.getStockId(), 1);
                List<Trade> allTradesOfOneStock = tradeRepository.findByUserIdAndStockIdAndTradeType(
                    userId,
                    trade.getStockId(), tradeType);
                TradesOfUserCorrespondToStockId.put(trade.getStockId(), allTradesOfOneStock);
            }
        }
        return TradesOfUserCorrespondToStockId;
    }

    private List<HoldingDto> calculationOfHolding(Map<Long, List<Trade>> buyTradesOfUser,
        Map<Long, List<Trade>> sellTradesOfUser) {
        List<HoldingDto> holdings = new ArrayList<>();

        for (Map.Entry<Long, List<Trade>> entry : buyTradesOfUser.entrySet()) {
            Long key = entry.getKey();
            List<Trade> value = entry.getValue();
            double buyPriceOfStock = value.get(0).getPrice();
            int buyQuantitiesOfStock = 0;
            for (Trade trade : value) {
                buyQuantitiesOfStock += trade.getQuantity();
            }
            double totalBuyPriceOfStock = buyQuantitiesOfStock * buyPriceOfStock;
            List<Trade> findSellTradesOfStock = sellTradesOfUser.get(key);
            double sellPriceOfStock = findSellTradesOfStock.get(0).getPrice();
            int sellQuantitiesOfStock = 0;
            if (!findSellTradesOfStock.isEmpty()) {
                for (Trade trade : findSellTradesOfStock) {
                    sellQuantitiesOfStock += trade.getQuantity();
                }
            }
            double totalSellPriceOfStock = sellQuantitiesOfStock * sellPriceOfStock;
            double avgBuyPriceOfStock = totalBuyPriceOfStock - totalSellPriceOfStock;
            int buyQuantities = buyQuantitiesOfStock - sellQuantitiesOfStock;
            avgBuyPriceOfStock /= buyQuantities;

            Double gainLoss = (sellPriceOfStock - avgBuyPriceOfStock) * sellQuantitiesOfStock;
            HoldingDto holdingDto = HoldingDto.builder()
                .stockName(stockRepository.findById(key).get().getStockName())
                .stockId(key)
                .quantity(buyQuantities)
                .buyPrice(avgBuyPriceOfStock)
                .currentPrice(sellPriceOfStock)
                .gainLoss(gainLoss)
                .build();
            holdings.add(holdingDto);
        }
        return holdings;
    }

    private PortfolioResponseDto calculationOfPortfolio(List<HoldingDto> holdings)
    {
        Double avgBuyPriceOfHoldings = 0.0;
        Double avgCurrentPriceOfHoldings = 0.0;
        double profitLoss = 0.0;
        for (HoldingDto holdingDto : holdings) {
            avgBuyPriceOfHoldings += holdingDto.getBuyPrice();
            avgCurrentPriceOfHoldings += holdingDto.getCurrentPrice();
        }
        avgBuyPriceOfHoldings /= holdings.size();
        avgCurrentPriceOfHoldings /= holdings.size();
        profitLoss = avgCurrentPriceOfHoldings - avgBuyPriceOfHoldings;
        profitLoss /= avgBuyPriceOfHoldings;
        profitLoss *= 100;
        float gainLoss = (float) profitLoss;
        PortfolioResponseDto portfolioResponseDto = PortfolioResponseDto.builder()
            .holdings(holdings)
            .totalPortfolioHoldings(holdings.size())
            .totalBuyPrice(avgBuyPriceOfHoldings)
            .totalGainLossPercentage(gainLoss)
            .build();
        return portfolioResponseDto;
    }
}
