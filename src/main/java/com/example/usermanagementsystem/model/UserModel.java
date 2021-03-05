package com.example.usermanagementsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "users_tb")
public class UserModel {


    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "dataCreated")
    private String dataCreated;

    @Column(name = "emailAddress")
    private String emailAddress;

    public UserModel() {
    }

    public UserModel(String id, String name, String dataCreated, String emailAddress) {
        this.id = id;
        this.name = name;
        this.dataCreated = dataCreated;
        this.emailAddress = emailAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataCreated() {
        return dataCreated;
    }

    public void setDataCreated(String dataCreated) {
        this.dataCreated = dataCreated;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}


