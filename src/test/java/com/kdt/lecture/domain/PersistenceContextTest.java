package com.kdt.lecture.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Slf4j
@SpringBootTest
public class PersistenceContextTest {

    @Autowired
    CustomerRepository repository;

    @Autowired
    EntityManagerFactory emf;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void save() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = new Customer(); // 비영속상태
        customer.setId(1L);
        customer.setFirstName("honggu");
        customer.setLastName("kang");

        entityManager.persist(customer); // 비영속 -> 영속 (영속화)
        transaction.commit(); // entityManager.flush();
    }

    @Test
    void DB_vali() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = new Customer(); // 비영속상태
        customer.setId(1L);
        customer.setFirstName("honggu");
        customer.setLastName("kang");

        entityManager.persist(customer); // 비영속 -> 영속 (영속화)
        transaction.commit(); // entityManager.flush();

        entityManager.detach(customer); // 영속 -> 준영속

        Customer selected = entityManager.find(Customer.class, 1L);
        log.info("{} {}", selected.getFirstName(), selected.getLastName());
    }

    @Test
    void cash_vali() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = new Customer(); // 비영속상태
        customer.setId(1L);
        customer.setFirstName("honggu");
        customer.setLastName("kang");

        entityManager.persist(customer); // 비영속 -> 영속 (영속화)
        transaction.commit(); // entityManager.flush();

        Customer selected = entityManager.find(Customer.class, 1L);
        log.info("{} {}", selected.getFirstName(), selected.getLastName());
    }

    @Test
    void change() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = new Customer(); // 비영속상태
        customer.setId(1L);
        customer.setFirstName("honggu");
        customer.setLastName("kang");

        entityManager.persist(customer); // 비영속 -> 영속 (영속화)
        transaction.commit(); // entityManager.flush();

        transaction.begin();
        customer.setFirstName("guppy");
        customer.setLastName("hong");

        transaction.commit();
    }

    @Test
    void delete() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = new Customer(); // 비영속상태
        customer.setId(1L);
        customer.setFirstName("honggu");
        customer.setLastName("kang");

        entityManager.persist(customer); // 비영속 -> 영속 (영속화)
        transaction.commit(); // entityManager.flush();

        transaction.begin();

        entityManager.remove(customer);

        transaction.commit();
    }

}
