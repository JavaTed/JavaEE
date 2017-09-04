package ua.kiev.prog.bank.dao;

import ua.kiev.prog.bank.models.BankAccount;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

public class DAOBankAccount {

    private DAOBankService.DAOEntity<BankAccount> ba;

    public DAOBankAccount(DAOBankService.DAOEntity<BankAccount> daoe) {
        ba = daoe;
    }

    public BankAccount find(String accountNo){
        Root<BankAccount> root = ba.getRoot();
        CriteriaBuilder cb = ba.getCb();
        ba.where(cb.and(cb.equal(root.get("accountNo"),accountNo)));
        return ba.getTypedQuery().getSingleResult();
    }
}
