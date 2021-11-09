package ua.konstantynov.hw29.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.konstantynov.hw29.entities.*;
import ua.konstantynov.hw29.enumerations.AlbumsNames;
import ua.konstantynov.hw29.enumerations.ArtistsNames;
import ua.konstantynov.hw29.enumerations.Genders;
import ua.konstantynov.hw29.enumerations.Tracks;
import ua.konstantynov.hw29.utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AlbumDao {
    public void save(Album album) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(album);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(Album album) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(album);
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
            Album album = session.get(Album.class, id);
            if (album != null) {
                session.delete(album);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Album get(String id) {
        Transaction transaction = null;
        Album album = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            album = session.get(Album.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return album;
    }

    public List<Album> getAll() {
        Transaction transaction = null;
        List<Album> albums = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            albums = session.createQuery(
                    "SELECT a FROM Album a", Album.class)
                    .list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return albums;
    }

    public void saveRandom(int count) {
        for (int i = 0; i < count; i++) {
            List<Tracks> tracksList = new ArrayList<>();
            for (int j = 0; j < ThreadLocalRandom.current().nextInt(1, Tracks.values().length); j++) {
                tracksList.add(Tracks.values()[ThreadLocalRandom.current().nextInt(Tracks.values().length)]);
            }
            Artist artist = new Artist();
            artist.setGender(Genders.values()[ThreadLocalRandom.current().nextInt(Genders.values().length)]);
            artist.setName(ArtistsNames.values()[ThreadLocalRandom.current().nextInt(ArtistsNames.values().length)]);
            Album album = new Album();
            album.setName(AlbumsNames.values()[ThreadLocalRandom.current().nextInt(AlbumsNames.values().length)]);
            album.setPrice(ThreadLocalRandom.current().nextInt(10000, 10050000) / 100d);
            album.setTracksList(tracksList);
            album.setArtist(artist);
            artist.setAlbum(album);
            save(album);
        }
    }
}