package com.demo.inheritance.singletable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // default is SINGLE_TABLE
@DiscriminatorColumn(name = "acc_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "owner")
    private String owner;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    public Account() {}

    public Account(String owner, BigDecimal balance, BigDecimal interestRate) {
        this.owner = owner;
        this.balance = balance;
        this.interestRate = interestRate;
    }

    public Integer getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
