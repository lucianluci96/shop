package org.myproject.shop.core.model;


import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Column;
import javax.persistence.Table;


@Table(name = "currentUser")

public class CurrentUser extends User {
	private static final long serialVersionUID = 1L;
	@Column(name = "userEntity")
    private UserEntity userEntity;


    public CurrentUser(UserEntity userEntity) {
        super(userEntity.getUsername()
                , userEntity.getPassword()
                , AuthorityUtils.createAuthorityList(userEntity.getRole().toString()));
        this.userEntity = userEntity;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public Long getId() {
        return userEntity.getId();
    }

    public String getRole() {
        return userEntity.getRole().getRoleName();
    }

}
