package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.mapper;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Requets.TradeRequestDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses.TradeResponseDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.Trade;
public class TradeMapper {
    public static TradeRequestDto mapToTradeRequestDto(Trade trade){
        TradeRequestDto tradeRequestDto =TradeRequestDto.builder().userId
                (trade.getUserId()).
                stockId(trade.getStockId()).
                tradeType(trade.getTradeType()).
                quantity(trade.getQuantity()).build();
        return tradeRequestDto;
    }

    // Convert UserDto into User JPA Entity
    public static Trade mapToTrade(TradeRequestDto tradeRequestDto){
        Trade trade = Trade.builder().
                userId(tradeRequestDto.getUserId()).
                stockId(tradeRequestDto.getStockId()).
                quantity(tradeRequestDto.getQuantity()).
                tradeType(tradeRequestDto.getTradeType()).build();
        return trade;
    }
    public static TradeResponseDto mapToTradeResponseDto(Trade trade){
        TradeResponseDto tradeResponseDto = TradeResponseDto.builder().userId
                        (trade.getUserId()).
                stockId(trade.getStockId()).
                tradeType(trade.getTradeType()).
                quantity(trade.getQuantity()).build();
        return tradeResponseDto;
    }
}
//@Mapper
//public interface TradeMapper {
//    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
//
//    @Mappings({
//            @Mapping(target = "postId", source = "postId"),
//            @Mapping(target = "content", source = "content"),
//            @Mapping(target = "likes", source = "likes"),
//            @Mapping(target = "dislikes", source = "dislikes")
//    })
//    PostDto postToPostDto(Post post);

