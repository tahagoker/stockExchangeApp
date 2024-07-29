package com.stock.stockExchangeApp.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class StockExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean liveInMarket;

    @ManyToMany
    @JoinTable(
            name = "StockExchangeStock",
            joinColumns = @JoinColumn(name = "stockExchangeId"),
            inverseJoinColumns = @JoinColumn(name = "stockId")
    )
    private Set<Stock> stocks = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isLiveInMarket() {
        return liveInMarket;
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setLiveInMarket(boolean liveInMarket) {
        this.liveInMarket = liveInMarket;
    }

    public void removeStock(Stock stock) {
        this.stocks.remove(stock);
        updateLiveInMarketStatus();
    }

    private void updateLiveInMarketStatus() {
        if (this.stocks.size() >= 5) {
            this.liveInMarket = true;
        } else {
            this.liveInMarket = false;
        }
    }
}
