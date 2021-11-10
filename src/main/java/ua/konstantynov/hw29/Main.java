//Please implement a hibernate-based application which allows you to manage music store.
//Your application must support the following functions:
//Manage albums
//Manage artists
//Manage buying albums
//Manage users
//Manage orderings

//Please adopt your local Hibernate application to be cloud-ready,
//deploy it to Heroku and share access to your app with me.
//Please implement an application which is able to calculate trigonometric functions
//(sin, cos, tan, ctg, arcsin, arccos) using SOLID.
package ua.konstantynov.hw29;

import ua.konstantynov.hw29.entities.Customer;
import ua.konstantynov.hw29.service.AlbumService;
import ua.konstantynov.hw29.service.CustomerService;

public class Main {
    public static final AlbumService ALBUM_SERVICE = new AlbumService();
    public static final CustomerService CUSTOMER_SERVICE = new CustomerService();

    public static void main(String[] args) {
        System.out.println("<<<<< Start application >>>>>");
        ALBUM_SERVICE.saveRandom(10);
        for (int i = 1; i <= 3; i++) {
            Customer customer = new Customer("CUSTOMER_NAME_" + i);
            System.out.println("Saving customer...");
            CUSTOMER_SERVICE.save(customer);
            System.out.println("Buying album...");
            System.out.println(CUSTOMER_SERVICE.buyRandomAlbum(customer));
        }
        System.out.println("<<<<< Done! >>>>>");
    }
}