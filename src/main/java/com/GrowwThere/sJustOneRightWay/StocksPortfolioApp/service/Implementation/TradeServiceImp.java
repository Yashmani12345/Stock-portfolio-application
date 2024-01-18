package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Implementation;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.enums.TradeType;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.error.InsufficientStockException;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.mapper.TradeMapper;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Requets.TradeRequestDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses.TradeResponseDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.Stock;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.Trade;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.User;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.error.StockNotFoundException;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.error.UserNotFoundException;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.repository.StockRepository;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.repository.TradeRepository;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.repository.UserRepository;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Interface.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.mapper.TradeMapper.mapToTradeResponseDto;

@Service
public class TradeServiceImp implements TradeService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private StockRepository stockRepository;
    @Override
    public TradeResponseDto createUserTrade(TradeRequestDto tradeRequestDto)
        throws StockNotFoundException, UserNotFoundException, InsufficientStockException {
        Trade trade= TradeMapper.mapToTrade(tradeRequestDto);
        Long stockId= tradeRequestDto.getStockId();
        Long userId= tradeRequestDto.getUserId();
        Optional<Stock> stock=stockRepository.findById(stockId);
        if(stock.isEmpty()){
            throw new StockNotFoundException("Stock not available.");
        }
        Optional<User>user=userRepository.findById(userId);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not available.");
        }
        TradeType tradeType=trade.getTradeType();
        int sumOfBuySTocksQuntities=0;
        int sumOfSellSTocksQuntities=0;
        TradeResponseDto tradeResponseDto;

        if(tradeType== TradeType.Sell)
        {
            List<Trade> BuyTrades=tradeRepository.findByUserIdAndStockIdAndTradeType(userId,stockId,TradeType.Buy);
            for(Trade buyTrades:BuyTrades)
            {
                sumOfBuySTocksQuntities+=buyTrades.getQuantity();
            }
            List<Trade> SellTrades=tradeRepository.findByUserIdAndStockIdAndTradeType(userId,stockId,TradeType.Sell);
            for(Trade sellTrades:SellTrades)
            {
                sumOfSellSTocksQuntities+=sellTrades.getQuantity();
            }


            if(sumOfBuySTocksQuntities-sumOfSellSTocksQuntities>=trade.getQuantity()) {
                Double tradePrice = stock.get().getOpeningPrice();
                trade.setPrice(tradePrice);
                Trade UserTrade = tradeRepository.save(trade);
                trade.setPrice(tradePrice);
                tradeResponseDto = mapToTradeResponseDto(UserTrade);
                return tradeResponseDto;
            }
            else{
                throw new InsufficientStockException("Insufficient quantity.");
            }
        }

        Double tradePrice = stock.get().getClosingPrice();
        trade.setPrice(tradePrice);
        Trade UserTrade = tradeRepository.save(trade);
        trade.setPrice(tradePrice);
        tradeResponseDto = mapToTradeResponseDto(UserTrade);
        return tradeResponseDto;
    }
}
