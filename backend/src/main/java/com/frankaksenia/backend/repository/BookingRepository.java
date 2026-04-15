package com.frankaksenia.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frankaksenia.backend.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

}
