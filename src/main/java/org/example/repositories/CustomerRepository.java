package org.example.repositories;

import jakarta.persistence.EntityManager;
import org.example.models.Customer;

public class CustomerRepository {

    private EntityManager entityManager;

    public CustomerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Iterable<Customer> findAllCustomer() {
        String jpql = "SELECT a FROM Customer a";
        var query = entityManager.createQuery(jpql, Customer.class);
        var customer = query.getResultList();
        return customer;
    }

    public Customer findCustomerById(int id) {
        Customer customer = entityManager.find(Customer.class, id);
        if (customer == null) {
            return null;
        }
        return customer;
    }

    public void insertCustomer(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void updateCustomer(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void deleteCustomerById(int id) {
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.getTransaction().begin();
        try {
            if (customer != null) {
                entityManager.remove(customer);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}
