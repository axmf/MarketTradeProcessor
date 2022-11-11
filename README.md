# MarketTradeProcessor

## Description 
> Our system processes a large number of market interactions per second. We would like you to build a market trade processor which consumes trade messages via an endpoint, processes those messages in some way, and delivers a front-end of processed information based on the consumed messages.

The aim of this project is to showcase a potential implementation of the above requirements. Given that the description is rather short, I've made a few assumptions of what is required.

There are three components:
- Api to accept new data
- Processing
- Front end

# Implementation notes
## Rest API

> Expose an endpoint that can consume trade messages. Trade messages will be POSTed (during review) to this endpoint and will take the JSON form of:
```json
{
    "userId": "134256",
    "currencyFrom": "EUR",
    "currencyTo": "GBP",
    "amountSell": 1000,
    "amountBuy": 747.10,
    "rate": 0.7471,
    "timePlaced": "24-JAN-22 10:27:44",
    "originatingCountry": "FR"
}
```

## Message Processing

> Process messages received via the message consumption endpoint. Depending on what you wish to do, these messages can be processed in different ways.

## Front end

> Render the data from the output of the other two components. Like a list of message, graphs based on processed data or a map with real-time visualizations.

# Ideas
This section contains tools/processes that could be set up in a real environment:

## General
- [ ] Automated pipeline to deploy infra
- [ ] Observability

## API
- [ ] Auth
