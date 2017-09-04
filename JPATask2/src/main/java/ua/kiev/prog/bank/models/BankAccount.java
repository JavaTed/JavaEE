package ua.kiev.prog.bank.models;


import javax.persistence.*;

@Entity
@Table(name = "BankAccounts")
public class BankAccount {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false,unique = true)
    private String accountNo;
    @Column(nullable = false)
    private Double saldo;
    @Column(nullable = false)
    private Currency currency;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id",nullable = false)
    private BankCustomer customer;


    public BankAccount() {
    }

    public BankAccount(String accountNo, double saldo, Currency currency) {
        this.accountNo = accountNo;
        this.saldo = saldo;
        this.currency = currency;
    }

    public BankAccount(String accountNo, double saldo, Currency currency, BankCustomer customer) {
        this.accountNo = accountNo;
        this.saldo = saldo;
        this.currency = currency;
        this.customer = customer;
        customer.addAccount(this);
    }
    private boolean checkSaldo(double delta){
        return this.saldo +  delta>0;

    }
    protected boolean changeSaldo(double delta){
            if (delta<0){
                if(checkSaldo(delta))
                    saldo = saldo + delta;
                else
                    return false;}
            else
                saldo = saldo + delta;
        System.out.println(accountNo+" changed: "+ delta);
        return true;
    }

    public Currency getCurrency() {
        return currency;
    }


    public String getAccountNo() {
        return accountNo;
    }

    protected Double getSaldo() {
        return saldo;
    }
}
