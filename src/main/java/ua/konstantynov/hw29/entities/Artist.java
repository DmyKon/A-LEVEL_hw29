package ua.konstantynov.hw29.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ua.konstantynov.hw29.enumerations.ArtistsNames;
import ua.konstantynov.hw29.enumerations.Genders;

import javax.persistence.*;

@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "artist_id")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ArtistsNames name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Genders gender;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Album album;

    @Override
    public String toString() {
        return "Artist{" +
                "id='" + id + '\'' +
                ", name=" + name +
                ", gender=" + gender +
                '}';
    }
}
