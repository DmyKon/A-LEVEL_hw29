package ua.konstantynov.hw29.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "order_id")
    private String id;

    @JoinColumn(name = "customer_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Customer customer;

    @JoinColumn(name = "album_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Album album;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", album=" + album +
                ", dateTime=" + dateTime +
                '}';
    }
}
