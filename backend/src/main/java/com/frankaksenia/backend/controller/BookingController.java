package com.frankaksenia.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.frankaksenia.backend.dto.BookingCreateRequest;
import com.frankaksenia.backend.dto.BookingResponse;
import com.frankaksenia.backend.model.Booking;
import com.frankaksenia.backend.service.BookingService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Endpoint to get all bookings (for testing purposes)
    @GetMapping("/bookings")
    @ResponseBody
    public List<Booking> getBookings() {
        return bookingService.getBookings();
    }

    @GetMapping("/studentBookings")
    public ResponseEntity<List<BookingResponse>> getBookingsByStudentId(@RequestParam UUID studentId) {
        List<BookingResponse> bookings = bookingService.getBookingsByStudentId(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(bookings); 
    }

    @GetMapping("/mentorBookings")
    public ResponseEntity<List<BookingResponse>> getBookingsByMentorId(@RequestParam UUID mentorId) {
        List<BookingResponse> bookings = bookingService.getBookingsByMentorId(mentorId);
        return ResponseEntity.status(HttpStatus.OK).body(bookings);
    }
    
    @PostMapping("/createBooking")
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingCreateRequest request) {
        BookingResponse bookingResponse = bookingService.createBooking(request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(bookingResponse);
    }
    

}
