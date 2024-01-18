package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Implementation;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses.StockResponseDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.Stock;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.error.StockNotFoundException;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.repository.StockRepository;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Interface.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.mapper.StockMapper.mapToStockResponseDto;

@Service
public class StockServiceImp implements StockService {
    @Autowired
    private StockRepository stockRepository;
    @Override
    public Stock createStock(Stock stock)
    {
        return stockRepository.save(stock);
    }
    public List<Stock> readStocksFromCsv(BufferedReader inputReader) throws IOException {
        String line = null;
        boolean firstLine = true;
        List<Stock> inputs = new ArrayList<>();
        while((line = inputReader.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue;
            }
            String[] columns = line.split(",");
            Long stockID = Long.parseLong(columns[0]);
            String stockName = columns[1];
            Double openingPrice = Double.parseDouble((columns[4]));
            Double highPrice = Double.parseDouble((columns[5]));
            Double lowPrice = Double.parseDouble((columns[6]));
            Double closingPrice = Double.parseDouble((columns[7]));
            Double lastPrice= Double.parseDouble((columns[8]));

            Stock stock = Stock.builder()
                    .stockId(stockID)
                    .stockName(stockName)
                    .openingPrice(openingPrice)
                    .closingPrice(closingPrice)
                    .highPrice(highPrice)
                    .lowPrice(lowPrice)
                    .lastPrice(lastPrice)
                    .build();

            inputs.add(stock);
        }
        return inputs;
    }
    @Override
    public String uploadCSVAndUpdateStocks(MultipartFile file) throws IOException {
        BufferedReader inputReader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
        List<Stock> inputs = readStocksFromCsv(inputReader);
        stockRepository.saveAll(inputs);
        return "Stock details updated successfully";
    }

    @Override
    public StockResponseDto fetchStockById(Long stockId) throws StockNotFoundException {
        Optional<Stock> stock = stockRepository.findById(stockId);
        if(stock.isEmpty()){
            throw new StockNotFoundException("Stock is not available");
        }
        return mapToStockResponseDto(stock.get());
    }
}
