package org.myproject.shop.service.api;

import java.util.List;

import org.myproject.shop.rest.dto.StockProduct;

public interface IStockService {
    void calculateStock();
    List<StockProduct> getStock();


}
