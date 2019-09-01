package com.demo.inheritance.singletable;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("DEBIT")
public class DebitAccount extends Account {

    @Column(name = "over_draft_fee")
    private BigDecimal overDraftFee;

    public DebitAccount() {}

    public BigDecimal getOverDraftFee() {
        return overDraftFee;
    }

    public void setOverDraftFee(BigDecimal overDraftFee) {
        this.overDraftFee = overDraftFee;
    }
}
