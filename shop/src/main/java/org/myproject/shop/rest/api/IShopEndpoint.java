package org.myproject.shop.rest.api;


import org.myproject.shop.rest.dto.ShopDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping(value = "/shops")
public interface IShopEndpoint {
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    List<ShopDto> getShops();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<ShopDto> getShop(@PathVariable long id);

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<ShopDto> addShop(@RequestBody ShopDto shop);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<ShopDto> deleteShop(@PathVariable long id);

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<ShopDto> updateShop(@PathVariable long id, @RequestBody ShopDto shop);
}
