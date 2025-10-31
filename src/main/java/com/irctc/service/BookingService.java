package com.irctc.service; import com.irctc.dto.BookingRequestDto; import com.irctc.entity.Booking;
public interface BookingService { Booking bookTicket(BookingRequestDto request); void cancelBooking(String pnr); Booking getBookingByPnr(String pnr); }
