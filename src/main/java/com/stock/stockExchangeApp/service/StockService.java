package com.stock.stockExchangeApp.service;

import com.stock.stockExchangeApp.model.Stock;
import com.stock.stockExchangeApp.model.StockExchange;
import com.stock.stockExchangeApp.repository.StockExchangeRepository;
import com.stock.stockExchangeApp.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockExchangeRepository stockExchangeRepository;

    public List<Stock> createStock(List<Stock> stocks) {
        return stockRepository.saveAll(stocks);
    }

    public List<Stock> listStocks() {
        return stockRepository.findAll();
    }

    public Stock updateStockPrice(Long stockId, BigDecimal newPrice) {
        Stock stock = stockRepository.findById(stockId).orElseThrow(() -> new RuntimeException("Stock not found"));
        stock.setCurrentPrice(newPrice);
        stock.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        return stockRepository.save(stock);
    }

    public void deleteStock(Long stockId) {
        //stockRepository.deleteById(stockId);

        Optional<Stock> optionalStock = stockRepository.findById(stockId);
        if (optionalStock.isPresent()) {
            Stock stock = optionalStock.get();
            // Hisse senedini listeleyen tüm borsaları al
            for (StockExchange stockExchange : stock.getStockExchanges()) {
                stockExchange.removeStock(stock);
                stockExchangeRepository.save(stockExchange); // Borsayı güncelle
            }
            // Hisse senedini veritabanından sil
            stockRepository.delete(stock);
        }
    }

}
