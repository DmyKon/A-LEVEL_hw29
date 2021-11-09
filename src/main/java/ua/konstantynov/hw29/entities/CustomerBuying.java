package ua.konstantynov.hw29.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "customer_buying")
public class CustomerBuying {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "customer_buying_id")
    private String id;

    @JoinColumn(name = "customer_id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Customer customer;

    @JoinColumn(name = "album_id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Album album;

    @Override
    public String toString() {
        return "CustomerBuying{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", album=" + album +
                '}';
    }
}
