package ua.kiev.prog.bank.dao;

import ua.kiev.prog.bank.models.Currency;
import ua.kiev.prog.bank.models.CurrencyRate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.Date;

public class DAOCurrencyRate {
    private DAOBankService.DAOEntity<CurrencyRate> cr;

    public DAOCurrencyRate(DAOBankService.DAOEntity<CurrencyRate> daoe) {
        cr = daoe;
    }

    public CurrencyRate find(Currency cur1, Currency cur2, Date date){
        CriteriaBuilder cb = cr.getCb();
        EntityManager em = cr.getEm();
        CurrencyRate crate;
        if (cur1 == cur2)
            return new CurrencyRate(cur1,cur2,date,1);
        Root<CurrencyRate> root = cr.getRoot();
        cr.where(cb.or(cb.and(cb.equal(root.get("cur1"),cur1),cb.equal(root.get("cur2"),cur2),cb.equal(root.get("rateDate"),date)),cb.and(cb.equal(root.get("cur1"),cur2),cb.equal(root.get("cur2"),cur1),cb.equal(root.get("rateDate"),date))));
        try{
            crate = cr.getTypedQuery().getSingleResult();
            if (crate.getCur1() != cur2)
                crate.inverse();
            em.detach(crate);
            return crate;
        }catch(NoResultException e){
            return null;
        }

    }
}
