package org.myproject.shop.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.myproject.shop.rest.dto.ShopDto;


@Entity
@Table(name = "shop")
public class ShopEntity extends  BaseEntity {
	private static final long serialVersionUID = 1L;
	@Column(name = "name")
    private String name;

    public ShopEntity(String name) {
        this.name = name;
    }

    public ShopEntity() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String toString() {

        return String.format("Shop[  name='%s' ]", name);

    }

    public ShopDto toDto() {

        return new ShopDto(this.getName());

    }

    public ShopEntity updateFromDto(ShopDto dto) {
        this.name = dto.getName();

        return this;
    }

    public ShopEntity createFromDto(ShopDto dto) {
        this.setId(dto.getId());
        this.setName(dto.getName());

        return this;
    }

}