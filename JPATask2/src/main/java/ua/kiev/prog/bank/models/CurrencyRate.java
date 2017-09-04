package ua.kiev.prog.bank.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CurrencyRates")
public class CurrencyRate {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private Currency cur1;
    @Column(nullable = false)
    private Currency cur2;
    @Column(nullable = false)
    private Date rateDate;
    @Column(nullable = false)
    private double rate;

    public CurrencyRate() {
    }

    public CurrencyRate(Currency cur1, Currency cur2, Date rateDate, double rate) {
        this.cur1 = cur1;
        this.cur2 = cur2;
        this.rateDate = rateDate;
        this.rate = rate;
    }


    //public double getRate() {
        //return rate;
    //}

    public void setRate(double rate) {
        this.rate = rate;
    }


    public Double convert(Double sum){
        return sum*rate;
    }

    public Currency getCur1() {
        return cur1;
    }

    public Currency getCur2() {
        return cur2;
    }

    public void inverse(){
        Currency tmp = cur1;
        cur1 = cur2;
        cur2 = tmp;
        rate = 1/rate;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "id#=" + id +
                ", cur1=" + cur1 +
                ", cur2=" + cur2 +
                ", rateDate=" + rateDate +
                ", rate=" + rate +
                '}';
    }
}
