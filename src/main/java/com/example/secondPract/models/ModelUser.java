package com.example.secondPract.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class ModelUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_user;
    private String fname, lname, login;
    private BigDecimal oklad;
    private String dateU;

    public long getId_user() { return id_user;}
    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public BigDecimal getOkald() {
        return oklad;
    }

    public void setOkald(BigDecimal okald) {
        this.oklad = oklad;
    }

    public String getDateU() {
        return dateU;
    }

    public void setDateU(String dateU) {
        this.dateU = dateU;
    }


    public ModelUser(String fname, String lname, String  login, BigDecimal okald, String dateU) {
        this.fname = fname;
        this.lname = lname;
        this.login = login;
        this.oklad = okald;
        this.dateU = dateU;
    }

    public ModelUser() {

    }
}
