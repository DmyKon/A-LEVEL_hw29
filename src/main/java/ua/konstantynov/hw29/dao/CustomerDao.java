package ua.konstantynov.hw29.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.konstantynov.hw29.entities.Album;
import ua.konstantynov.hw29.entities.Customer;
import ua.konstantynov.hw29.entities.CustomerBuying;
import ua.konstantynov.hw29.entities.Order;
import ua.konstantynov.hw29.service.AlbumService;
import ua.konstantynov.hw29.utils.HibernateUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CustomerDao {
    public void save(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            if (customer != null) {
                session.delete(customer);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Customer get(String id) {
        Transaction transaction = null;
        Customer customer = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            customer = session.get(Customer.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return customer;
    }

    public List<Customer> getAll() {
        Transaction transaction = null;
        List<Customer> customers = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            customers = session.createQuery(
                    "SELECT c FROM Customer c", Customer.class)
                    .list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return customers;
    }

    public Order buyRandomAlbum(Customer customer) {
        CustomerBuying customerBuying = new CustomerBuying();
        List<Album> albumList = new AlbumService().getAll();
        if (albumList.isEmpty()) {
            return null;
        } else {
            Album album = albumList.get(ThreadLocalRandom.current().nextInt(albumList.size()));
            customerBuying.setAlbum(album);
            customer.setCustomerBuying(customerBuying);
            customerBuying.setCustomer(customer);
            Order order = new Order();
            order.setAlbum(album);
            order.setCustomer(customer);
            order.setDateTime(LocalDateTime.now());
            customer.setOrder(order);
            update(customer);
            return order;
        }
    }
}
