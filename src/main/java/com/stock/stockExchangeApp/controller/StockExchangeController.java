package com.stock.stockExchangeApp.controller;


import com.stock.stockExchangeApp.model.Stock;
import com.stock.stockExchangeApp.model.StockExchange;
import com.stock.stockExchangeApp.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/v1/stock-exchange")
public class StockExchangeController {
    @Autowired
    private StockExchangeService stockExchangeService;

    @PostMapping("/")
    public StockExchange createStockExchange(@RequestBody StockExchange stockExchange, @RequestParam Set<Long> stockIds) {
        return stockExchangeService.createStockExchange(stockExchange, stockIds);
    }

    @PostMapping("/{name}")
    public StockExchange addStockToStockExchange(@PathVariable String name, @RequestParam Set<Long> stockIds) {
        return stockExchangeService.addStockToStockExchange(name, stockIds);
    }

    @GetMapping("/")
    public List<StockExchange> listStockExchanges() {
        return stockExchangeService.listStockExchanges();
    }

    @GetMapping("/{name}")
    public StockExchange getStockExchangeByName(@PathVariable String name) {
        return stockExchangeService.getStockExchangeByName(name);
    }

    @DeleteMapping("/{name}")
    public StockExchange deleteStockFromStockExchange(@PathVariable String name, @RequestParam Long stockId) {
        return stockExchangeService.deleteStockFromStockExchange(name, stockId);
    }

}

