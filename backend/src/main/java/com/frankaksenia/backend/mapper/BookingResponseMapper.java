package com.frankaksenia.backend.mapper;

import org.springframework.stereotype.Component;

import com.frankaksenia.backend.dto.BookingResponse;
import com.frankaksenia.backend.model.Booking;

@Component
public class BookingResponseMapper {

    public BookingResponse mapToBookingResponse(Booking booking) {
        return new BookingResponse(
            booking.getId(),
            booking.getMentor().getId(),
            booking.getStudent().getId(),
            booking.getStartTime(),
            booking.getEndTime(),
            booking.getStatus(),
            booking.getTopic(),
            booking.getMessage()
        );
    }   

}
