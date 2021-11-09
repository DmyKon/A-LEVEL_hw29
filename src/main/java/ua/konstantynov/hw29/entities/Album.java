package ua.konstantynov.hw29.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ua.konstantynov.hw29.enumerations.AlbumsNames;
import ua.konstantynov.hw29.enumerations.Tracks;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "album_id")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private AlbumsNames name;

    @Column(name = "price")
    private Double price;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Tracks.class)
    private List<Tracks> tracksList;

    @JoinColumn(name = "artist_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Artist artist;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "album", fetch = FetchType.LAZY)
    private CustomerBuying customerBuying;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "album", fetch = FetchType.LAZY)
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return id.equals(album.id) && name == album.name &&
                Objects.equals(price, album.price) &&
                Objects.equals(artist, album.artist) &&
                Objects.equals(customerBuying, album.customerBuying) &&
                Objects.equals(order, album.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, artist, customerBuying, order);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", name=" + name +
                ", price=" + price +
                ", tracksList=" + tracksList +
                '}';
    }
}
