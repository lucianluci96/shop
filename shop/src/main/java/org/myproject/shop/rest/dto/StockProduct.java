package org.myproject.shop.rest.dto;

public class StockProduct extends BaseDto {
	private static final long serialVersionUID = 1L;
	private ProductDto product;
    private long quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockProduct that = (StockProduct) o;

        if (quantity != that.quantity) return false;
        return product != null ? product.equals(that.product) : that.product == null;

    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (int) (quantity ^ (quantity >>> 32));
        return result;
    }

    public StockProduct() {

    }

    public StockProduct(ProductDto product) {
        this.product = product;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Long in(Long quant) {
        this.quantity = this.quantity + quant;

        return this.quantity;
    }

    public Long out(Long quant) {
        this.quantity = this.quantity - quant;

        return this.quantity;
    }


}

