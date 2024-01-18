package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Interface;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses.StockResponseDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.Stock;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.error.StockNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StockService {
    Stock createStock(Stock stock);

    String uploadCSVAndUpdateStocks(MultipartFile file) throws IOException;

    StockResponseDto fetchStockById(Long stockId) throws StockNotFoundException;
}
