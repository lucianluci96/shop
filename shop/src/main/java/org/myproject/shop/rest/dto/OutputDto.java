package org.myproject.shop.rest.dto;

public class OutputDto extends BaseDto {
	private static final long serialVersionUID = 1L;
	private long id;
	private ProductDto product;
    private ShopDto shop;
    private long quantity;

    public OutputDto() {

    }

    public OutputDto(ProductDto product, ShopDto shop, long quantity) {

        this.product = product;
        this.shop = shop;
        this.quantity = quantity;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public ShopDto getShop() {
        return shop;
    }

    public void setShop(ShopDto shop) {
        this.shop = shop;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}