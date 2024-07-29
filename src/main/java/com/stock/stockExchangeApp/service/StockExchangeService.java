package com.stock.stockExchangeApp.service;

import com.stock.stockExchangeApp.model.Stock;
import com.stock.stockExchangeApp.model.StockExchange;
import com.stock.stockExchangeApp.repository.StockExchangeRepository;
import com.stock.stockExchangeApp.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StockExchangeService {
   @Autowired
    private StockExchangeRepository stockExchangeRepository;

    @Autowired
    private StockRepository stockRepository;
    public StockExchange createStockExchange(StockExchange stockExchange, Set<Long> stockIds) {
        Set<Stock> stocks = new HashSet<>(stockRepository.findAllById(stockIds));
        if(stocks.size() == 0){
            throw new RuntimeException("Stock not found");
        }
        StockExchange stockExchangeNew = stockExchangeRepository.findByName(stockExchange.getName()).orElse(stockExchange);
        stockExchangeNew.getStocks().addAll(stocks);
        if (stockExchangeNew.getStocks().size() >= 5) {
            stockExchangeNew.setLiveInMarket(true);
        }
        return stockExchangeRepository.save(stockExchangeNew);
    }

    public StockExchange addStockToStockExchange(String stockExchangeName, Set<Long> stockIds) {
        Set<Stock> stocks = new HashSet<>(stockRepository.findAllById(stockIds));
        if(stocks.size() == 0){
            throw new RuntimeException("No Stock found");
        }
        StockExchange stockExchange = stockExchangeRepository.findByName(stockExchangeName)
                .orElseThrow(() -> new RuntimeException("No StockExchange found"));

        stockExchange.getStocks().addAll(stocks);
        if (stockExchange.getStocks().size() >= 5) {
            stockExchange.setLiveInMarket(true);
        }
        return stockExchangeRepository.save(stockExchange);
    }

    public List<StockExchange> listStockExchanges(){
        List<StockExchange> stockExchanges  = stockExchangeRepository.findAll();
        return stockExchanges;
    }

    public StockExchange getStockExchangeByName(String name) {
        return stockExchangeRepository.findByName(name).orElseThrow(() -> new RuntimeException("Stock Exchange not found"));
    }

    public StockExchange deleteStockFromStockExchange(String stockExchangeName, Long stockId) {
        StockExchange stockExchange = stockExchangeRepository.findByName(stockExchangeName).orElseThrow(() -> new RuntimeException("Stock Exchange not found"));
        Stock stock = stockRepository.findById(stockId).orElseThrow(() -> new RuntimeException("Stock not found"));
        stockExchange.getStocks().remove(stock);
        if (stockExchange.getStocks().size() < 5) {
            stockExchange.setLiveInMarket(false);
        }
        return stockExchangeRepository.save(stockExchange);
    }

}
