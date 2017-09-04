package ua.kiev.prog.bank.dao;

import ua.kiev.prog.bank.models.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.Arrays;

public class DAOBankService {

    public class DAOEntity<E> {
        private Class<E> cl;
        private EntityManager em;
        private CriteriaBuilder cb;
        private CriteriaQuery<E> cq;
        private Root<E> root;

        public DAOEntity(Class<E> cl) {
            em = DAOBankService.this.getEm();
            cb = em.getCriteriaBuilder();
            this.cl = cl;
            cq = cb.createQuery(cl);
            root = cq.from(cl);
        }

        public CriteriaQuery<E> getCriteriaQuery(){
            return cq;
        }

        public Root<E> getRoot() {
            return root;
        }

        public CriteriaBuilder getCb() {
            return cb;
        }

        public EntityManager getEm() {
            return em;
        }

        public void where(Expression ex){
            cq.where(ex);
        }

        public TypedQuery<E> getTypedQuery(){
            TypedQuery<E> tq = em.createQuery(cq);
            return tq;
        }
    }

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATask2");;
    private EntityManager em = emf.createEntityManager();

    private static DAOBankService ourInstance = new DAOBankService();
    private DAOEntity<CurrencyRate> cr = new DAOEntity<CurrencyRate>(CurrencyRate.class);
    private DAOEntity<BankAccount> ba = new DAOEntity<BankAccount>(BankAccount.class);
    private DAOEntity<BankCustomer> bc = new DAOEntity<BankCustomer>(BankCustomer.class);
    private DAOBankAccount DAOba = new DAOBankAccount(ba);
    private DAOCurrencyRate DAOcr = new DAOCurrencyRate(cr);
    private DAOBankCustomer DAObc = new DAOBankCustomer(bc);

    public static DAOBankService getInstance() {
        return ourInstance;
    }

    private DAOBankService() {
    }

    public EntityManager getEm() {
        return em;
    }

    private void addEntity(Object... obj){
        em.getTransaction().begin();
        Arrays
                .stream(obj)
                .forEach(em::persist);
        em.getTransaction().commit();
    }
    public void addCurrencyRate(CurrencyRate... rate){
        addEntity(rate);
    }

    public void addBankCustomer(BankCustomer... customer){
        addEntity(customer);
    }

    public void close(){
        em.close();
        emf.close();
    }

    public CurrencyRate findCurrencyRate(Currency cur1, Currency cur2, Date date){
        return DAOcr.find(cur1, cur2, date);
    }

    public BankAccount findBankAccount(String accountNo){
        return DAOba.find(accountNo);
    }

    public boolean doTransaction(Transaction transaction){
        em.getTransaction().begin();
        transaction.doTransaction();
        if (transaction.isDone()){
            em.persist(transaction);
            em.getTransaction().commit();
            return true;}
        else{
            em.detach(transaction);
            em.getTransaction().rollback();
        return false;}
    }
}
