package com.javaclimb.music.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/*管理员*/
public class Admin implements Serializable {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
