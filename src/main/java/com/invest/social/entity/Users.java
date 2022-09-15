package com.invest.social.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "ossn_users")
public class Users {
    @Id
    private Long guid;
    private String username;
    private String activation;

    public Long getGuid() {
        return guid;
    }
    public void setGuid(Long guid) {
        this.guid = guid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getActivation() {
        return activation;
    }
    public void setActivation(String activation) {
        this.activation = activation;
    }
}
