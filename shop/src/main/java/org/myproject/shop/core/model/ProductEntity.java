package org.myproject.shop.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.myproject.shop.rest.dto.ProductDto;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity product = (ProductEntity) o;

        return name.equals(product.name);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    public ProductEntity() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductEntity(String name) {
        this.name = name;


    }

    public String toString() {

        return String.format("Product[name='%s' ]", name);

    }

    public ProductDto toDto() {

        return new ProductDto(this.getId(), this.getName());


    }

    public ProductEntity updateFromDto(ProductDto dto) {
        this.name = dto.getName();

        return this;
    }

    public ProductEntity createFromDto(ProductDto dto) {

        this.setName(dto.getName());

        return this;
    }
}
