package ua.konstantynov.hw29.service;

import ua.konstantynov.hw29.dao.CustomerDao;
import ua.konstantynov.hw29.entities.Customer;
import ua.konstantynov.hw29.entities.Order;

import java.util.List;

public class CustomerService {
    private static final CustomerDao CUSTOMER_DAO = new CustomerDao();

    public void save(Customer customer) {
        CUSTOMER_DAO.save(customer);
    }

    public void update(Customer customer) {
        CUSTOMER_DAO.update(customer);
    }

    public void delete(String id) {
        CUSTOMER_DAO.delete(id);
    }

    public Customer get(String id) {
        return CUSTOMER_DAO.get(id);
    }

    public List<Customer> getAll() {
        return CUSTOMER_DAO.getAll();
    }

    public Order buyRandomAlbum(Customer customer) {
        return CUSTOMER_DAO.buyRandomAlbum(customer);
    }
}