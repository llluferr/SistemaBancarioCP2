package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.models.Bank;
import org.example.repositories.BankRepository;
import org.example.repositories.CustomerRepository;


public class Main {
    public static void main(String[] args) {
    try {
        EntityManagerFactory entityManagerFactory = Persistence.
                createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        var bankRepository = new BankRepository(entityManager);
        var customerRepository = new CustomerRepository(entityManager);

        var customer = customerRepository.findCustomerById(1);
        customer.setName("Paulo");
        customerRepository.insertCustomer(customer);
        customerRepository.deleteCustomerById(1);
        customerRepository.updateCustomer(customer);

        var novoBanco = new Bank("JorgeBank");
        novoBanco.setName(null);
        bankRepository.updateBank(novoBanco);


        entityManager.close();
        entityManagerFactory.close();
    } catch (Exception e){
        throw e;
        }
    }
}