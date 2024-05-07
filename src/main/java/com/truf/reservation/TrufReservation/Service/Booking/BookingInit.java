package com.truf.reservation.TrufReservation.Service.Booking;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BookingInit implements ApplicationListener<ContextRefreshedEvent> {

    private final BookingServiceImpl bookingService;

    public BookingInit(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        bookingService.init();
    }
}
