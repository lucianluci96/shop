package org.myproject.shop.service.api.impl;

import org.myproject.shop.core.model.ShopEntity;
import org.myproject.shop.core.repository.ShopRepository;
import org.myproject.shop.rest.dto.ShopDto;
import org.myproject.shop.service.api.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService implements IShopService {

    @Autowired
    ShopRepository shopRepository;

    @Override
    public List<ShopDto> getShops() {
        List<ShopDto> shops = new ArrayList<>();
        for (ShopEntity shopEntity : shopRepository.findAll())
            shops.add(shopEntity.toDto());

        return shops;
    }

    @Override
    public ShopDto getShop(long id) {
        if (!shopRepository.exists(id)) {

            return null;
        }

        return (shopRepository.findOne(id).toDto());
    }

    @Override
    public boolean addShop(ShopDto shop) {
        shopRepository.save(new ShopEntity().updateFromDto(shop));

        return true;
    }

    @Override
    public boolean updateShop(long id) {
        if (!shopRepository.exists(id)) {

            return false;
        }
        ShopEntity shopToUpdate = shopRepository.findOne(id);
        shopRepository.save(shopToUpdate);

        return true;
    }

    @Override
    public boolean deleteShop(long id) {
        if (!shopRepository.exists(id)) {

            return false;
        }
        shopRepository.delete(id);

        return true;
    }
}