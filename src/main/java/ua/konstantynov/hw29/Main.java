//Please implement a hibernate-based application which allows you to manage music store.
//Your application must support the following functions:
//Manage albums
//Manage artists
//Manage buying albums
//Manage users
//Manage orderings
package ua.konstantynov.hw29;

import ua.konstantynov.hw29.entities.Customer;
import ua.konstantynov.hw29.entities.Order;
import ua.konstantynov.hw29.service.AlbumService;
import ua.konstantynov.hw29.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final AlbumService ALBUM_SERVICE = new AlbumService();
    public static final CustomerService CUSTOMER_SERVICE = new CustomerService();

    public static void main(String[] args) {
        ALBUM_SERVICE.saveRandom(10);
        List<Order> orderList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Customer customer = new Customer("CUSTOMER_NAME_" + i);
            CUSTOMER_SERVICE.save(customer);
            orderList.add(CUSTOMER_SERVICE.buyRandomAlbum(customer));
        }
        orderList.forEach(System.out::println);
    }
}