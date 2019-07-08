package org.myproject.shop.rest.dto;


public class ManagerDto extends BaseDto {
	private static final long serialVersionUID = 1L;
	private long id;
    private String firstName;
    private String lastName;

    public ManagerDto(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ManagerDto() {

    }

}

