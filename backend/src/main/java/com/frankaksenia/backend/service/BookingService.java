package com.frankaksenia.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.frankaksenia.backend.dto.BookingCreateRequest;
import com.frankaksenia.backend.dto.BookingResponse;
import com.frankaksenia.backend.exceptions.BookingConflictException;
import com.frankaksenia.backend.exceptions.ResourceNotFoundException;
import com.frankaksenia.backend.exceptions.UnauthorisedActionException;
import com.frankaksenia.backend.mapper.BookingResponseMapper;
import com.frankaksenia.backend.model.Booking;
import com.frankaksenia.backend.model.BookingStatus;
import com.frankaksenia.backend.model.User;
import com.frankaksenia.backend.model.Role;
import com.frankaksenia.backend.repository.BookingRepository;
import com.frankaksenia.backend.repository.UserRepository;


@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final BookingResponseMapper bookingResponseMapper;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, BookingResponseMapper bookingResponseMapper) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.bookingResponseMapper = bookingResponseMapper;
    }

    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByStudentId(UUID studentId) {
        return bookingRepository.findByStudentId(studentId);
    }

    public List<Booking> getBookingsByMentorId(UUID mentorId) {
        return bookingRepository.findByMentorId(mentorId);
    }

    public BookingResponse createBooking(BookingCreateRequest request, Authentication authentication) {
    
        User student = getCurrentUser(authentication);
        
        User mentor = userRepository.findById(request.mentorId()).orElseThrow(() -> new ResourceNotFoundException("User", "Mentor not found"));

        if(mentor.getRole() != Role.MENTOR) 
            throw new UnauthorisedActionException("Invalid User Role", "User with id " + mentor.getId() + " is not a mentor");

        boolean hasConflict = bookingRepository.existsByMentorAndStatusAndStartTimeLessThanAndEndTimeGreaterThan(
            mentor, BookingStatus.ACCEPTED, request.endTime(), request.startTime()
        );

        if(hasConflict) 
            throw new BookingConflictException("Booking Conflict", "There is a conflict with an existing booking");
        
        Booking booking = new Booking();
        booking.setMentor(mentor);
        booking.setStudent(student);
        booking.setStartTime(request.startTime());
        booking.setEndTime(request.endTime());
        booking.setStatus(BookingStatus.PENDING);
        booking.setTopic(request.topic());
        booking.setMessage(request.message());      
        booking.setMeetingLink(null);
        booking.setCreatedAt(LocalDateTime.now());

        Booking newBoooking = bookingRepository.save(booking);

        return bookingResponseMapper.mapToBookingResponse(newBoooking);
    }

    private User getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}


