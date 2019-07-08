package org.myproject.shop.core.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.myproject.shop.rest.dto.InputDto;


@Entity
@Table(name = "input")
public class InputEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@NotNull
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private ShopEntity shop;

    @Column(name = "quantity")
    private long quantity;

    public InputEntity() {

    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setLong(long quantity) {
        this.quantity = quantity;
    }

    public ShopEntity getShop() {
        return shop;
    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }

    public InputEntity(ProductEntity product, ShopEntity shop, long quantity) {
        this.product = product;
        this.shop = shop;
        this.quantity = quantity;

    }

    public String toString() {

        return String.format("Shop[ product='%s'  , shop='%s', quantity='%s']", product, shop, quantity);

    }

    public InputDto toDto() {

        return new InputDto(this.getProduct().toDto(), this.getShop().toDto(), this.getQuantity());

    }

    public InputEntity updateFromDto(InputDto dto) {
        this.setProduct(new ProductEntity().createFromDto(dto.getProduct()));
        this.setShop(new ShopEntity().createFromDto(dto.getShop()));
        this.quantity = dto.getQuantity();

        return this;
    }
}
