package com.stock.stockExchangeApp.controller;


import com.stock.stockExchangeApp.model.Stock;
import com.stock.stockExchangeApp.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/api/v1/stock")
public class StockController {
    @Autowired
    private StockService stockService;


    @PostMapping("/")
    public List<Stock> createStock(@RequestBody List<Stock> stock) {
        return stockService.createStock(stock);
    }

    @GetMapping("/")
    public List<Stock> listStocks() {
        return stockService.listStocks();
    }


    @PutMapping("/{stockId}")
    public Stock updateStockPrice(@PathVariable Long stockId, @RequestParam BigDecimal price) {
        return stockService.updateStockPrice(stockId, price);
    }

    @DeleteMapping("/{stockId}")
    public void deleteStock(@PathVariable Long stockId) {
        stockService.deleteStock(stockId);
    }
}

