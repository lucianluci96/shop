package org.myproject.shop.service.api;


import org.myproject.shop.rest.dto.ProductDto;
import java.util.List;

public interface IProductService {
    List<ProductDto> getProducts();

    ProductDto getProduct(long id);

    boolean addProduct(ProductDto product);

    boolean updateProduct(long id) ;

    boolean deleteProduct(long id);

}
