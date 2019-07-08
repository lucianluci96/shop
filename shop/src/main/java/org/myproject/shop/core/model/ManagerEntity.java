package org.myproject.shop.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.myproject.shop.rest.dto.ManagerDto;


@Entity
@Table(name = "manager")
public class ManagerEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    public ManagerEntity(){

    }

    public ManagerEntity(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {return firstName;}

    public String getLastName() {
        return lastName;}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ManagerEntity(String firstName, String lastName){
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public String toString() {

        return String.format("Manager[firstName='%s' , lastName='%s']" , firstName , lastName) ;

    }
    public ManagerDto toDto() {

        return new ManagerDto(this.getFirstName(),this.getLastName());

    }

    public ManagerEntity createFromDto(ManagerDto dto) {
        this.setId(dto.getId());
        this.setFirstName(dto.getFirstName());
        this.setLastName(dto.getLastName());

        return this;
    }

    public ManagerEntity updateFromDto(ManagerDto dto){
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();

        return this;
    }
}

