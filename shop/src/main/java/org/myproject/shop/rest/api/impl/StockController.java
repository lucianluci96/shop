package org.myproject.shop.rest.api.impl;


import org.myproject.shop.rest.api.IStockEndpoint;
import org.myproject.shop.rest.dto.StockProduct;
import org.myproject.shop.service.api.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class StockController implements IStockEndpoint {


    @Autowired
    IStockService stockService;

    @Override
    public List<StockProduct> getStock() {

        return stockService.getStock();
    }
}
