package com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.service.Implementation;
import static org.junit.jupiter.api.Assertions.*;

import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.dto.Responses.StockResponseDto;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.entity.Stock;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.error.StockNotFoundException;
import com.GrowwThere.sJustOneRightWay.StocksPortfolioApp.repository.StockRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StockServiceImpTest {

  @InjectMocks
  private StockServiceImp stockServiceImp;
  @Mock
  private StockRepository stockRepository;

  private static StockResponseDto stockDto = StockResponseDto.builder()
      .stockId(500002L)
      .stockName("ABB LTD.")
      .openingPrice(4766)
      .highPrice(4836.5)
      .lowPrice(4684.9)
      .closingPrice(4745.45)
      .lastPrice(4742)
      .build();
  private static Stock stock = Stock.builder()
      .stockId(500002L)
      .stockName("ABB LTD.")
      .openingPrice(4766)
      .highPrice(4836.5)
      .lowPrice(4684.9)
      .closingPrice(4745.45)
      .lastPrice(4742)
      .build();

  @Test
  public void WhenValidReadFromCsv_ReadIt() throws IOException {
    StringReader reader = new StringReader(
        "SC_CODE,SC_NAME,SC_GROUP,SC_TYPE,OPEN,HIGH,LOW,CLOSE,LAST,PREVCLOSE,NO_TRADES,NO_OF_SHRS,NET_TURNOV,TDCLOINDI\n"
            +
            "500002,ABB LTD.,A,Q,4766,4836.5,4684.9,4745.45,4727.8,4742,1172,5884,28100618\n" +
            "500003,AEGIS LOGIS,A,Q,378.05,379.5,373.15,377.85,377.85,375.65,1703,51875,19522528\n"
            +
            "500008,ARE&M,A,Q,770.8,771.15,749.45,752,752,763.45,2091,29561,22358767");
    try {
      List<Stock> found = stockServiceImp.readStocksFromCsv(new BufferedReader(reader));
      assertEquals(3, found.size());
      assertEquals(500002, found.get(0).getStockId());
      assertEquals(500003, found.get(1).getStockId());
      assertEquals(500008, found.get(2).getStockId());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void WhenValidStockId_fetchStockDetails() throws StockNotFoundException {
    Long stockId = 500002L;

    Mockito.when(
            stockRepository.findById(stock.getStockId()))
        .thenReturn(Optional.of(stock));

    StockResponseDto found = stockServiceImp.fetchStockById(stockId);
    assertEquals(stockDto, found);
  }

  @Test
  public void WhenNotValidStockId_ReturnEmpty() throws StockNotFoundException {
    Mockito.when(stockRepository.findById(stock.getStockId()))
        .thenReturn(Optional.empty());

//    StockResponseDto found = stockService.fetchStockById(stock.getStockId());
    assertThrows(StockNotFoundException.class, () -> stockServiceImp.fetchStockById(50000003L));
  }
}