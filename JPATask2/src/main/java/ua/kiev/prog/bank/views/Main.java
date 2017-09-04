package ua.kiev.prog.bank.views;

import ua.kiev.prog.bank.models.*;
import ua.kiev.prog.bank.dao.DAOBankService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {




    public static void main(String[] args) {
        DAOBankService dao = DAOBankService.getInstance();
        BankCustomer bc1 = new BankCustomer("123223322332","Ivan","Ivanov", Date.valueOf(LocalDate.of(1988,12,12)),"BE1212122");
        bc1.addAccount("122223433",1000, Currency.UAH);
        new BankAccount("122223434",100, Currency.USD, bc1);
        dao.addBankCustomer(bc1);
        System.out.println(bc1.getId());

        CurrencyRate cr = new CurrencyRate(Currency.UAH,Currency.USD,Date.valueOf(LocalDate.of(2017,9,1)),25.55);
        CurrencyRate cr2 = new CurrencyRate(Currency.UAH,Currency.USD,Date.valueOf(LocalDate.now()),25.56);
        CurrencyRate cr3 = new CurrencyRate(Currency.UAH,Currency.EUR,Date.valueOf(LocalDate.now()),29.33);
        dao.addCurrencyRate(cr,cr2,cr3);

        System.out.println("Today currency "+dao.findCurrencyRate(Currency.UAH,Currency.USD,Date.valueOf(LocalDate.of(2017,8,30))));
        //Customer can transfer  money to  any account
        bc1.transferMoney("122223433","122223434",100,Currency.UAH);

        //Customer can tranaddsfer  money to his account
        bc1.addMoneyToMyAccount("122223433",120.0,Currency.UAH);
        //Customer can get amount of all money from all accounts in selected currency
        System.out.println("All my money "+bc1.getAllMoney(Currency.UAH));
        new Scanner(System.in).nextLine();

        dao.close();

    }
}
