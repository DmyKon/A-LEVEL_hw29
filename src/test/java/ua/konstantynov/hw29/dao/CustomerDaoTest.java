package ua.konstantynov.hw29.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.konstantynov.hw29.entities.Album;
import ua.konstantynov.hw29.entities.Customer;

import java.util.ArrayList;
import java.util.List;

class CustomerDaoTest {
    private static CustomerDao CUSTOMER_DAO;
    Customer customer1;
    Customer customer2;
    Customer customer3;

    @BeforeAll
    static void beforeAll() {
        CUSTOMER_DAO = new CustomerDao();
    }

    @BeforeEach
    void setUp() {
        customer1 = new Customer("CUSTOMER_NAME_1");
        customer2 = new Customer("CUSTOMER_NAME_2");
        customer3 = new Customer("CUSTOMER_NAME_3");
    }

    @Test
    void save() {
        CUSTOMER_DAO.save(customer1);
        Assertions.assertEquals(customer1, CUSTOMER_DAO.get(customer1.getId()));
    }

    @Test
    void update() {
        CUSTOMER_DAO.save(customer1);
        customer1.setName("TEST TEST TEST");
        CUSTOMER_DAO.update(customer1);
        Assertions.assertEquals(customer1, CUSTOMER_DAO.get(customer1.getId()));
    }

    @Test
    void delete() {
        CUSTOMER_DAO.save(customer1);
        CUSTOMER_DAO.delete(customer1.getId());
        Assertions.assertEquals(0, CUSTOMER_DAO.getAll().size());
    }

    @Test
    void get() {
        CUSTOMER_DAO.save(customer1);
        Assertions.assertEquals(customer1, CUSTOMER_DAO.get(customer1.getId()));
    }

    @Test
    void getAll() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
        CUSTOMER_DAO.save(customer1);
        CUSTOMER_DAO.save(customer2);
        CUSTOMER_DAO.save(customer3);
        Assertions.assertEquals(customerList, CUSTOMER_DAO.getAll());
    }

    @Test
    void buyRandomAlbum() {
        AlbumDao albumDao = new AlbumDao();
        albumDao.save(new Album());
        CUSTOMER_DAO.save(customer1);
        CUSTOMER_DAO.buyRandomAlbum(customer1);
        Assertions.assertNotNull(CUSTOMER_DAO.get(customer1.getId()).getCustomerBuying().getAlbum());
    }
}