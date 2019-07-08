package org.myproject.shop.service.api.impl;

import org.myproject.shop.core.model.OutputEntity;
import org.myproject.shop.core.model.ProductEntity;
import org.myproject.shop.core.model.ShopEntity;
import org.myproject.shop.core.repository.OutputRepository;
import org.myproject.shop.core.repository.ProductRepository;
import org.myproject.shop.core.repository.ShopRepository;
import org.myproject.shop.rest.dto.OutputDto;
import org.myproject.shop.service.api.IOutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OutputService implements IOutputService {

   @Autowired
    OutputRepository outputRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<OutputDto> getOutputs() {
        List<OutputDto> outputs = new ArrayList<>();
        for (OutputEntity output  : outputRepository.findAll())
            outputs.add(output.toDto());

        return outputs;
    }

    @Override
    public OutputDto getOutput(long id) {
        if (!outputRepository.exists(id)) {
            return null;
        }

        return (outputRepository.findOne(id).toDto());
    }

    @Override
    public boolean addOutput(OutputDto output) {
        OutputEntity outputEntity= new OutputEntity();
        ShopEntity shop = shopRepository.findByName(output.getShop().getName()).get(0);
        ProductEntity productEntity = productRepository.findByName(output.getProduct().getName()).get(0);
        outputEntity.setShop(shop);
        outputEntity.setProduct(productEntity);
        outputEntity.setLong(output.getQuantity());
        outputRepository.save(outputEntity);

        return true;
    }
}

