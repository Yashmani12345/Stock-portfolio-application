package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Implementation;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses.HoldingDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses.PortfolioResponseDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses.StockResponseDto;
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
    public PortfolioResponseDto fetchUserPortfolioById(Long userId)
    {
        List<Trade> userBuyTrades=tradeRepository.findByUserIdAndTradeType(userId, TradeType.Buy);
        Map<Long,Integer> stockIdMap = new HashMap<>();
        List<HoldingDto> holdings=new ArrayList<>();
        for (Trade buyTrades:userBuyTrades)
        {
            if(!stockIdMap.containsKey(buyTrades.getStockId())){
                stockIdMap.put(buyTrades.getStockId(),1);
                List<Trade> BuyTrades=tradeRepository.findByUserIdAndStockIdAndTradeType(userId,buyTrades.getStockId(),TradeType.Buy);
                Double BuyPriceOfOneHolding=0.0;
                int BuyQuantitiesOfOneHolding=0;
                for(Trade trades:BuyTrades)
                {
                    BuyPriceOfOneHolding+=trades.getPrice()*trades.getQuantity();
                    BuyQuantitiesOfOneHolding+=trades.getQuantity();
                    BuyPriceOfOneHolding/=BuyQuantitiesOfOneHolding;
                }
                Double gainLoss=stockRepository.findById(buyTrades.getStockId()).get().getClosingPrice()-BuyPriceOfOneHolding;
                HoldingDto holdingDto =HoldingDto.builder()
                        .StackName(stockRepository.findById(buyTrades.getStockId()).get().getStockName())
                        .StockId(buyTrades.getStockId())
                        .quantity(buyTrades.getQuantity())
                        .BuyPrice(BuyPriceOfOneHolding)
                        .CurrentPrice(stockRepository.findById(buyTrades.getStockId()).get().getClosingPrice())
                        .GainLoss(gainLoss)
                        .build();
                holdings.add(holdingDto);
            }
        }
        Double avgBuyPriceOfHoldings=0.0;
        Double avgCurrentPriceOfHoldings=0.0;
        double profitLoss=0.0;
        for(HoldingDto holdingDto:holdings)
        {
            avgBuyPriceOfHoldings+=holdingDto.getBuyPrice();
            avgCurrentPriceOfHoldings+=holdingDto.getCurrentPrice();
        }
        avgBuyPriceOfHoldings/=holdings.size();
        avgCurrentPriceOfHoldings/=holdings.size();
        profitLoss=avgCurrentPriceOfHoldings-avgBuyPriceOfHoldings;
        profitLoss/=avgBuyPriceOfHoldings;
        profitLoss*=100;
        float gainLoss=(float) profitLoss;
        PortfolioResponseDto portfolioResponseDto =PortfolioResponseDto.builder()
                .holdings(holdings)
                .TotalPortfolioHoldings(holdings.size())
                .TotalBuyPrice(avgBuyPriceOfHoldings)
                .TotalGainLossPercentage(gainLoss)
                .build();
        return portfolioResponseDto;
    }
}
