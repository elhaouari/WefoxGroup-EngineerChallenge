package com.nelhaouari.wefoxchallenge.service.impl.db.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class AccountEntity {
    @Id
    private String account_id;
    private String name;
    private String email;
    private Date birthdate;
    private Date last_payment_date;
    private Date created_on;

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getLast_payment_date() {
        return last_payment_date;
    }

    public void setLast_payment_date(Date last_payment_date) {
        this.last_payment_date = last_payment_date;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }
}
