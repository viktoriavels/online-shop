package ru.vels.storage.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "BOOKINGS")
public class Bookings implements Serializable {

    @EmbeddedId
    private BookingId id;

    @Column(name = "COUNT", nullable = false, length = 100)
    private Long count;

    public BookingId getId() {
        return id;
    }

    public void setId(BookingId id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
