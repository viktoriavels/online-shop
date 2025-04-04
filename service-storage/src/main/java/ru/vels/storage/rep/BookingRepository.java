package ru.vels.storage.rep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.vels.storage.entity.BookingId;
import ru.vels.storage.entity.Bookings;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, BookingId>, JpaSpecificationExecutor<Bookings> {
    List<Bookings> findById_OrderId(String orderId);
}
