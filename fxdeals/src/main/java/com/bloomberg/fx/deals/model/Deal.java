package com.bloomberg.fx.deals.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "deals")
public class Deal implements Serializable {
    @Id
    @NotNull
    @Column(name = "deal_id", unique=true)
    private String deal_id;

    @Column(name = "from_currency", nullable=false)
    private String from_currency;

    @Column(name = "to_currency", nullable=false)
    private String to_currency;

    @Column(name = "amount")
    private float amount;

    @Column(name= "deal_date")
    private Timestamp deal_date;

    public Deal(){
    }

    public Deal(String deal_id, String from_currency, String to_currency, float amount, Timestamp deal_date) {
        this.from_currency = from_currency;
        this.to_currency = to_currency;
        this.amount = amount;
        this.deal_date = deal_date;
    }

    public String getDeal_id() {
        return deal_id;
    }

    public void setDeal_id(String deal_id) {
        this.deal_id = deal_id;
    }

    public String getFrom_currency() {
        return from_currency;
    }

    public void setFrom_currency(String from_currency) {
        this.from_currency = from_currency;
    }

    public String getTo_currency() {
        return to_currency;
    }

    public void setTo_currency(String to_currency) {
        this.to_currency = to_currency;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Timestamp getDeal_date() {
        return deal_date;
    }

    public void setDeal_date(Timestamp deal_date) {
        this.deal_date = deal_date;
    }

    @Override
    public String toString() {
        return "Fx Deal [id=" + deal_id + ", ordering currency=" + from_currency +
                ", recipient currency=" + to_currency + ", amount=" + amount +
                ", deal date & time =" + deal_date + "]";
    }
}
