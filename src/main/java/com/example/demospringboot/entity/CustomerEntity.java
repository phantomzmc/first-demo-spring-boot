package com.example.demospringboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @Column(name = "first_name", columnDefinition = "varchar(100) default ''")
    private String firstName;
    @Column(name = "last_name", columnDefinition = "varchar(100) default ''")
    private String lastName;
    @Column(name = "tel" , columnDefinition = "varchar(10) default ''")
    private String tel;
    @Column(name = "email", columnDefinition = "varchar(100) default ''")
    private String email;
    @Column(name = "status_id", columnDefinition = "integer default 1")
    private int statusId;

    public CustomerEntity() {
    }

    public CustomerEntity(String firstName, String lastName, String tel, String email, int statusId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tel = tel;
        this.email = email;
        this.statusId = statusId;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", statusId=" + statusId +
                '}';
    }
}
