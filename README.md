# Stock Exchange Management Case Project

## General Purpose

This project is related to stock exchange management. Each stock exchange can have many stocks. However, a stock exchange with fewer than 5 stocks cannot be live in the market (i.e., `liveInMarket` is false). A particular stock can be listed in many stock exchanges, and all properties of a stock will remain the same across all exchanges.

## API Endpoints

You can find all endpoint in this link = https://documenter.getpostman.com/view/2553103/2sA3kbex3y


## Setup and Rund

git clone https://github.com/tahagoker/stockExchangeApp.git

cd stockExchangeApp

./mvnw clean package

docker build -t stock-exchange-app .

docker run -p 8080:8080 stock-exchange-app


