package org.myproject.shop.rest.api;

import org.myproject.shop.rest.dto.StockProduct;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@RequestMapping(value = "/stocks")
public interface IStockEndpoint {
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    List<StockProduct> getStock();
}
