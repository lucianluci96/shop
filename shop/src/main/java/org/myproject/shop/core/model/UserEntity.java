package org.myproject.shop.core.model;


import org.myproject.shop.rest.dto.RoleEnum;
import org.myproject.shop.rest.dto.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column
    private Date created;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public UserEntity() {
    }

    public UserEntity(String username, String password, RoleEnum role) {
        this.username = username;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.role = role;
        this.created = new Date();
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public UserDto toDto() {
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setRole(role);

        return userDto;
    }

    public UserEntity updateFromDto(UserDto dto) {
        this.username = dto.getUsername();

        return this;
    }
}