package ua.kiev.prog.bank.dao;

import ua.kiev.prog.bank.models.BankCustomer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

public class DAOBankCustomer {

    private DAOBankService.DAOEntity<BankCustomer> bc;

    public DAOBankCustomer(DAOBankService.DAOEntity<BankCustomer> daoe) {
        bc = daoe;
    }

    private BankCustomer find(String field, String value){
        Root<BankCustomer> root = bc.getRoot();
        CriteriaBuilder cb = bc.getCb();
        bc.where(cb.and(cb.equal(root.get("accountNo"),value)));

        return bc.getTypedQuery().getSingleResult();
    }

    public BankCustomer findByPassport(String passportNo){
        return find("passport",passportNo);
    }
}
