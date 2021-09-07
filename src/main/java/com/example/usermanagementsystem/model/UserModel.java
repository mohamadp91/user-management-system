package com.example.usermanagementsystem.model;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class UserModel {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "creationtime")
    private String creationTime;

    @Column(name = "emailaddress")
    private String emailAddress;

    public UserModel() {
    }

    public UserModel(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public UserModel(String firstName, String lastName, String creationTime, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationTime = creationTime;
        this.emailAddress = emailAddress;
    }

    public UserModel(long id , String firstName, String lastName, String creationTime, String emailAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationTime = creationTime;
        this.emailAddress = emailAddress;
    }


    public Long getId() {
        return id;
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

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String dataCreated) {
        this.creationTime = dataCreated;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
