package ua.kiev.prog.bank.models;

import ua.kiev.prog.bank.dao.DAOBankService;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name = "Transactions")
public class Transaction {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    @JoinColumn(name = "first_account_id")
    private BankAccount accountFrom;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    @JoinColumn(name = "second_account_id")
    private BankAccount accountTo;
    private double sum;
    private Currency currency;
    @Column(nullable = false)
    private Timestamp transactionDate;
    private boolean done;

    public Transaction(BankAccount accountFrom, BankAccount accountTo, double sum, Currency currency) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.sum = sum;
        this.currency = currency;
    }

    public Transaction() {}

    public BankAccount getAccountFrom() {
        return accountFrom;
    }

    public BankAccount getAccountTo() {
        return accountTo;
    }

    public double getSum() {
        return sum;
    }

    public boolean isDone() {
        return done;
    }


    private boolean doPart(BankAccount ba, double sum){
        try {
            if (ba != null)
                ba.changeSaldo(sum);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public Transaction doTransaction(){
        DAOBankService dao = DAOBankService.getInstance();
        LocalDateTime ldt = LocalDateTime.now();
        Date d = Date.valueOf(ldt.toLocalDate());
        transactionDate = Timestamp.valueOf(ldt);
        CurrencyRate crFrom;
        CurrencyRate crTo;
        if (getAccountFrom() != null)
            crFrom = dao.findCurrencyRate(getAccountFrom().getCurrency(),this.currency,d);
        else
            crFrom = dao.findCurrencyRate(this.currency,this.currency,d);
        if (getAccountTo() != null)
            crTo = dao.findCurrencyRate(this.currency,getAccountTo().getCurrency(),d);
        else
            crTo = dao.findCurrencyRate(this.currency,this.currency,d);


        if (doPart(accountFrom, crFrom.convert(-sum)))
            if (doPart(accountTo, crTo.convert(sum))){
                done = true;
                return this;}
        return null;
    }
}
