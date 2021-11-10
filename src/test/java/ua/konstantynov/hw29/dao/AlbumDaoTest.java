package ua.konstantynov.hw29.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.konstantynov.hw29.entities.Album;

import java.util.ArrayList;
import java.util.List;

class AlbumDaoTest {
    private static AlbumDao ALBUM_DAO;
    Album album1;
    Album album2;
    Album album3;

    @BeforeAll
    static void beforeAll() {
        ALBUM_DAO = new AlbumDao();
    }

    @BeforeEach
    void setUp() {
        album1 = new Album();
        album2 = new Album();
        album3 = new Album();
    }

    @Test
    void save() {
        ALBUM_DAO.save(album1);
        Assertions.assertEquals(album1, ALBUM_DAO.get(album1.getId()));
    }

    @Test
    void update() {
        ALBUM_DAO.save(album1);
        album1.setPrice(1000.123);
        ALBUM_DAO.update(album1);
        Assertions.assertEquals(album1, ALBUM_DAO.get(album1.getId()));
    }

    @Test
    void delete() {
        ALBUM_DAO.save(album1);
        ALBUM_DAO.delete(album1.getId());
        Assertions.assertEquals(0, ALBUM_DAO.getAll().size());
    }

    @Test
    void get() {
        ALBUM_DAO.save(album1);
        Assertions.assertEquals(album1, ALBUM_DAO.get(album1.getId()));
    }

    @Test
    void getAll() {
        List<Album> customerList = new ArrayList<>();
        customerList.add(album1);
        customerList.add(album2);
        customerList.add(album3);
        ALBUM_DAO.save(album1);
        ALBUM_DAO.save(album2);
        ALBUM_DAO.save(album3);
        Assertions.assertEquals(customerList, ALBUM_DAO.getAll());
    }

    @Test
    void saveRandom() {
        int countBeforeSave = ALBUM_DAO.getAll().size();
        ALBUM_DAO.saveRandom(10);
        Assertions.assertEquals(countBeforeSave + 10, ALBUM_DAO.getAll().size());
    }
}