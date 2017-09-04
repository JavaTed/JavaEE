package ua.kiev.prog.bank.models;

import ua.kiev.prog.bank.dao.DAOBankService;

import javax.persistence.*;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BankCustomers")
public class BankCustomer {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false, unique = true)
    private String personalId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private Date birthdate;
    @Column(nullable = false, unique = true)
    private String passport;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<BankAccount> listBA = new ArrayList<>();

    public BankCustomer() {
    }

    public BankCustomer(String personalId, String name, String surname, Date birthdate, String passport) {
        this.personalId = personalId;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.passport = passport;
    }

    public int getId() {
        return id;
    }

    public String getPersonalId() {
        return personalId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getPassport() {
        return passport;
    }

    public BankAccount addAccount(String accountNo, double saldo, Currency currency){
        BankAccount ba = new BankAccount(accountNo,saldo,currency,this);
        addAccount(ba);
        return ba;
    }

    public void addAccount(BankAccount bankAccount){
        if (!listBA.contains(bankAccount))
            listBA.add(bankAccount);
    }

    public BankAccount getMyAccount(String accountNo){
        return listBA.stream()
                .filter(t->t.getAccountNo().equals(accountNo))
                .findFirst()
                .orElse(null);
    }

    public void addMoneyToMyAccount(String accountNo,Double amount, Currency currency){
        DAOBankService dao = DAOBankService.getInstance();
        BankAccount ba = getMyAccount(accountNo);
        if (ba != null){
            dao.doTransaction(new Transaction(null,ba,
                    amount,
                    currency));
        }
    }

    public void transferMoney(String accountNoFrom,String accountNoTo,double amount,Currency currency){
        DAOBankService dao = DAOBankService.getInstance();
        BankAccount ba = getMyAccount(accountNoFrom);
        BankAccount baTo = dao.findBankAccount(accountNoTo);
        if (ba != null && baTo != null){
            dao.doTransaction(new Transaction(
                        ba,
                        baTo,
                        amount,
                        currency));
        }
    }

    public Double getAllMoney(Currency currency){
        Double amountTotal;
        BankAccount ba = new BankAccount("----",0, currency);

        listBA.stream()
                .filter(t->t.getAccountNo()!="----")
                .peek(t->System.out.println("+++"+t.getSaldo()))
                .map(t -> new Transaction(null,
                                 ba,
                                 t.getSaldo(),
                                 t.getCurrency()))

                .forEach(t->t.doTransaction());
        amountTotal = ba.getSaldo();
        return  amountTotal;
    }
}
