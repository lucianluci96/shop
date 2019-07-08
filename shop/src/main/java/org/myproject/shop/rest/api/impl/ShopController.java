package org.myproject.shop.rest.api.impl;

import org.myproject.shop.rest.api.IShopEndpoint;
import org.myproject.shop.rest.dto.ShopDto;
import org.myproject.shop.service.api.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class ShopController implements IShopEndpoint {

    @Autowired
    private IShopService shopService;

    @Override
    public List<ShopDto> getShops() {
        return shopService.getShops();
    }

    @Override
    public ResponseEntity<ShopDto> getShop(@PathVariable long id) {
        if (shopService.getShop(id)==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return new ResponseEntity<>(shopService.getShop(id) , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ShopDto> addShop(@RequestBody ShopDto shop) {
        shopService.addShop(new ShopDto());

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @Override
    public ResponseEntity<ShopDto> deleteShop(@PathVariable long id) {
        if (!shopService.deleteShop(id)) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        shopService.deleteShop(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    public ResponseEntity<ShopDto> updateShop(@PathVariable long id, @RequestBody ShopDto shop) {
        if (!shopService.updateShop(id)) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ShopDto shopToUpdate = shopService.getShop(id);
        shopToUpdate.setName(shop.getName());
        shopService.getShop(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }




}
