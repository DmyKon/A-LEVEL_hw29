package ua.konstantynov.hw29.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.konstantynov.hw29.entities.Artist;
import ua.konstantynov.hw29.enumerations.ArtistsNames;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class ArtistDaoTest {
    private static ArtistDao ARTIST_DAO;
    Artist artist1;
    Artist artist2;
    Artist artist3;

    @BeforeAll
    static void beforeAll() {
        ARTIST_DAO = new ArtistDao();
    }

    @BeforeEach
    void setUp() {
        artist1 = new Artist();
        artist2 = new Artist();
        artist3 = new Artist();
    }

    @Test
    void save() {
        ARTIST_DAO.save(artist1);
        Assertions.assertEquals(artist1, ARTIST_DAO.get(artist1.getId()));
    }

    @Test
    void update() {
        ARTIST_DAO.save(artist1);
        artist1.setName(ArtistsNames.values()[ThreadLocalRandom.current().nextInt(ArtistsNames.values().length)]);
        ARTIST_DAO.update(artist1);
        Assertions.assertEquals(artist1, ARTIST_DAO.get(artist1.getId()));
    }

    @Test
    void delete() {
        ARTIST_DAO.save(artist1);
        ARTIST_DAO.delete(artist1.getId());
        Assertions.assertEquals(0, ARTIST_DAO.getAll().size());
    }

    @Test
    void get() {
        ARTIST_DAO.save(artist1);
        Assertions.assertEquals(artist1, ARTIST_DAO.get(artist1.getId()));
    }

    @Test
    void getAll() {
        List<Artist> artistList = new ArrayList<>();
        artistList.add(artist1);
        artistList.add(artist2);
        artistList.add(artist3);
        ARTIST_DAO.save(artist1);
        ARTIST_DAO.save(artist2);
        ARTIST_DAO.save(artist3);
        Assertions.assertEquals(artistList, ARTIST_DAO.getAll());
    }
}