package org.myproject.shop.rest.dto;


public class UserDto extends BaseDto {
	private static final long serialVersionUID = 1L;
	private long id;
    private String username;
    private String password;
    private RoleEnum role;

    public UserDto() {
    }

    public UserDto(String username, String password, RoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}