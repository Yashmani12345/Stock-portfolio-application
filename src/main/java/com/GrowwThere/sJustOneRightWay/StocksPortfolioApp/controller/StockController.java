package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.controller;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses.StockResponseDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.Stock;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.error.StockNotFoundException;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Interface.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("")
public class StockController {
    @Autowired
    private StockService stockService;
    @PostMapping("/stock")
    public Stock createStock(@RequestBody Stock stock)
    {
        return stockService.createStock(stock);
    }

    @PostMapping("/upload-stocks")
    public ResponseEntity<String> uploadCSVAndUpdateStocks(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
        String message=stockService.uploadCSVAndUpdateStocks(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(message);
    }
    @GetMapping("/stocks/{id}")
    public StockResponseDto fetchStockById(@PathVariable("id") Long stockId) throws StockNotFoundException
    {
        return stockService.fetchStockById(stockId);
    }
}
