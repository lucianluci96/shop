package org.myproject.shop.service.api;

import org.myproject.shop.rest.dto.ShopDto;
import java.util.List;


public interface IShopService {
    List<ShopDto> getShops();

    ShopDto getShop(long id);

    boolean addShop(ShopDto shop);

    boolean updateShop(long id);

    boolean deleteShop(long id);
}
