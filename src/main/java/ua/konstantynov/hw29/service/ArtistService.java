package ua.konstantynov.hw29.service;

import ua.konstantynov.hw29.dao.ArtistDao;
import ua.konstantynov.hw29.entities.Artist;

import java.util.List;

public class ArtistService {
    private static final ArtistDao ARTIST_DAO = new ArtistDao();

    public void save(Artist artist) {
        ARTIST_DAO.save(artist);
    }

    public void update(Artist artist) {
        ARTIST_DAO.update(artist);
    }

    public void delete(String id) {
        ARTIST_DAO.delete(id);
    }

    public Artist get(String id) {
        return ARTIST_DAO.get(id);
    }

    public List<Artist> getAll() {
        return ARTIST_DAO.getAll();
    }
}
