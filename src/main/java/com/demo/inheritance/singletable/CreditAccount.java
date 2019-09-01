package com.demo.inheritance.singletable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("CREDIT") // defaults to class name
public class CreditAccount extends Account {

    @Column(name = "credit_limit")
    private BigDecimal creditLimit;

    public CreditAccount() {}

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
}
