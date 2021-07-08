package com.nelhaouari.wefoxchallenge.service.impl.db.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class PaymentEntity {
    @Id
    private String payment_id;
    private Integer account_id;
    private String payment_type;
    private String credit_card;
    private Double amount;

    @Override
    public String toString() {
        return "Payment{" +
                "payment_id='" + payment_id + '\'' +
                ", account_id=" + account_id +
                ", payment_type='" + payment_type + '\'' +
                ", credit_card='" + credit_card + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentEntity payment = (PaymentEntity) o;
        return Objects.equals(payment_id, payment.payment_id) && Objects.equals(account_id, payment.account_id) && Objects.equals(payment_type, payment.payment_type) && Objects.equals(credit_card, payment.credit_card) && Objects.equals(amount, payment.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payment_id, account_id, payment_type, credit_card, amount);
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(String credit_card) {
        this.credit_card = credit_card;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

