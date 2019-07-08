package org.myproject.shop.rest.api.impl;

import org.myproject.shop.rest.api.IProductEndpoint;
import org.myproject.shop.rest.dto.ProductDto;
import org.myproject.shop.service.api.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
public class ProductController implements IProductEndpoint {

    @Autowired
    private IProductService productService;


    @Override
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @Override
    public ResponseEntity<ProductDto> getProduct(@PathVariable long id) {
        if (productService.getProduct(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto product) {
        productService.addProduct(new ProductDto());

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @Override
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable long id) {
        if (!productService.deleteProduct(id)) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        productService.deleteProduct(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    public ResponseEntity<ProductDto> updateProduct(@PathVariable long id, @RequestBody ProductDto product) {
        if (!productService.updateProduct(id)) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        ProductDto productToUpdate = productService.getProduct(id);
        productToUpdate.setName(product.getName());
        productService.getProduct(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
