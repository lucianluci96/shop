package org.myproject.shop.service.api.impl;

import org.myproject.shop.core.model.InputEntity;
import org.myproject.shop.core.model.ProductEntity;
import org.myproject.shop.core.model.ShopEntity;
import org.myproject.shop.core.repository.InputRepository;
import org.myproject.shop.core.repository.ProductRepository;
import org.myproject.shop.core.repository.ShopRepository;
import org.myproject.shop.rest.dto.InputDto;
import org.myproject.shop.service.api.IInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class InputService implements IInputService {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<InputDto> getInputs() {
        List<InputDto> inputs = new ArrayList<>();
        for (InputEntity input  : inputRepository.findAll())
            inputs.add(input.toDto());

        return inputs;
    }

    @Override
    public InputDto getInput(long id) {
        if (!inputRepository.exists(id)) {

            return null;
        }

        return (inputRepository.findOne(id).toDto());
    }

    @Override
    public boolean addInput(InputDto input) {
        InputEntity inputEntity = new InputEntity();
        ShopEntity shop = shopRepository.findByName(input.getShop().getName()).get(0);
        ProductEntity productEntity = productRepository.findByName(input.getProduct().getName()).get(0);

        inputEntity.setShop(shop);
        inputEntity.setProduct(productEntity);
        inputEntity.setLong(input.getQuantity());

        inputRepository.save(inputEntity);

        return true;
    }


}

