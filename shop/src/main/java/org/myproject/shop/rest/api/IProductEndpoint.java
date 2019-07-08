package org.myproject.shop.rest.api;

import org.myproject.shop.rest.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@RequestMapping(value = "/products")
public interface IProductEndpoint {
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    List<ProductDto> getProducts();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<ProductDto> getProduct(@PathVariable long id);

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto product);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<ProductDto> deleteProduct(@PathVariable long id);

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<ProductDto> updateProduct(@PathVariable long id, @RequestBody ProductDto product);
}
