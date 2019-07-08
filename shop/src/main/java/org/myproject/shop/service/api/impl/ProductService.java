package org.myproject.shop.service.api.impl;

import org.myproject.shop.core.model.ProductEntity;
import org.myproject.shop.core.repository.ProductRepository;
import org.myproject.shop.rest.dto.ProductDto;
import org.myproject.shop.service.api.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getProducts() {
        List<ProductDto> products = new ArrayList<>();
        for (ProductEntity product  : productRepository.findAll())
            products.add(product.toDto());

        return products;
    }

    @Override
    public ProductDto getProduct(long id) {
        if (!productRepository.exists(id)) {

            return null;
        }

        return (productRepository.findOne(id).toDto());
    }

    @Override
    public boolean addProduct(ProductDto product) {
        productRepository.save(new ProductEntity().updateFromDto(product));

        return true;
    }

    @Override
    public boolean updateProduct(long id) {
        if (!productRepository.exists(id)) {

            return false;
        }
        ProductEntity productToUpdate = productRepository.findOne(id);
        productRepository.save(productToUpdate);

        return true;
    }

    @Override
    public boolean deleteProduct(long id) {
        if (!productRepository.exists(id)) {

            return false;
        }
        productRepository.delete(id);

        return true;
    }


}
