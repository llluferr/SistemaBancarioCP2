package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.models.Bank;

public class BankRepository {

    private EntityManager entityManager;

    public BankRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Iterable<Bank> findAllBank() {
        String jpql = "SELECT a FROM Bank a";
        var query = entityManager.createQuery(jpql, Bank.class);
        var bank = query.getResultList();
        return bank;
    }

    public Bank findBankById(int id) {
        Bank bank = entityManager.find(Bank.class, id);
        if (bank == null) {
            return null;
        }
        return bank;
    }

    public void insertBank(Bank bank) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(bank);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void updateBank(Bank bank) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(bank);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void deleteBankById(int id) {
        Bank bank = entityManager.find(Bank.class, id);
        entityManager.getTransaction().begin();
        try {
            if (bank != null) {
                entityManager.remove(bank);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}
