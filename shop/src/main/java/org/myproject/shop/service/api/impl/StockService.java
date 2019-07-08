package org.myproject.shop.service.api.impl;


import org.myproject.shop.core.model.InputEntity;
import org.myproject.shop.core.model.OutputEntity;
import org.myproject.shop.core.repository.InputRepository;
import org.myproject.shop.core.repository.OutputRepository;
import org.myproject.shop.rest.dto.ProductDto;
import org.myproject.shop.rest.dto.StockProduct;
import org.myproject.shop.service.api.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StockService implements IStockService {
    private Map<ProductDto, StockProduct> stockProductMap = new HashMap<>();


    @Autowired
    private InputRepository inputRepository;
    @Autowired
    private OutputRepository outputRepository;

    public void calculateStock() {
        List<InputEntity> inputs = (List<InputEntity>) inputRepository.findAll();
        List<OutputEntity> outputs = (List<OutputEntity>) outputRepository.findAll();

        stockProductMap.clear();

        handleInputs(inputs);

        handleOutputs(outputs);
    }

    private void handleOutputs(List<OutputEntity> outputs) {
        for (OutputEntity output : outputs) {
            StockProduct stockProduct = stockProductMap.get((output.getProduct()).toDto());
            stockProduct.out(output.getQuantity());
        }
    }

    private void handleInputs(List<InputEntity> inputs) {
        for (InputEntity input : inputs) {
            StockProduct stockProduct = stockProductMap.get(input.getProduct().toDto());

            if (stockProduct == null) {
                stockProduct = new StockProduct(input.getProduct().toDto());
                stockProductMap.put(input.getProduct().toDto(), stockProduct);
            }

            stockProduct.in(input.getQuantity());
        }
    }

    @Override
    public List<StockProduct> getStock() {
        calculateStock();
        List<StockProduct> stockProducts = new ArrayList<>(stockProductMap.values());
        Collections.sort(stockProducts, new Comparator<StockProduct>() {
            @Override
            public int compare(StockProduct stockProduct, StockProduct t1) {

                return stockProduct.getProduct().getName().compareToIgnoreCase(t1.getProduct().getName());
            }
        });

        return stockProducts;
    }

}