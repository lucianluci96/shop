package org.myproject.shop.rest.dto;

public class ShopDto extends BaseDto {
	private static final long serialVersionUID = 1L;
	private long id;
    private String name;

    public ShopDto(String name) {
        this.name = name;


    }

    public ShopDto() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

