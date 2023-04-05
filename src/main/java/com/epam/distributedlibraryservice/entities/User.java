package com.epam.distributedlibraryservice.entities;

import jakarta.persistence.*;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "user_status", nullable = false, length = 45)
    private String userStatus;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login", length = 19)
    private Date lastLogin;
    @Column(name = "wrong_password_attempt")
    private Integer wrongPasswordAttempt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_wrong_password_attempt", length = 19)
    private Date lastWrongPasswordAttempt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time", nullable = false, length = 19)
    private Date creationTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", nullable = false, length = 19)
    private Date updateTime;
    @Column(name = "phone", length = 15)
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "city", length = 100)
    private String city;

    @PrePersist
    protected void onCreate() {
        creationTime = new Date();
        updateTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = new Date();
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getWrongPasswordAttempt() {
        return wrongPasswordAttempt;
    }

    public void setWrongPasswordAttempt(Integer wrongPasswordAttempt) {
        this.wrongPasswordAttempt = wrongPasswordAttempt;
    }

    public Date getLastWrongPasswordAttempt() {
        return lastWrongPasswordAttempt;
    }

    public void setLastWrongPasswordAttempt(Date lastWrongPasswordAttempt) {
        this.lastWrongPasswordAttempt = lastWrongPasswordAttempt;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
