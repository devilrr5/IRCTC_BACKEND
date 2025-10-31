package com.irctc.service.impl; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import com.irctc.repository.*; import com.irctc.service.*; import com.irctc.dto.BookingRequestDto; import com.irctc.entity.*; import java.util.UUID;
@Service public class BookingServiceImpl implements com.irctc.service.BookingService {
    private final BookingRepository bookingRepository; private final TrainRepository trainRepository; private final com.irctc.service.PaymentService paymentService; private final PassengerRepository passengerRepository; private final com.irctc.repository.UserRepository userRepository;
    public BookingServiceImpl(BookingRepository bookingRepository, TrainRepository trainRepository, com.irctc.service.PaymentService paymentService, PassengerRepository passengerRepository, com.irctc.repository.UserRepository userRepository){
        this.bookingRepository=bookingRepository; this.trainRepository=trainRepository; this.paymentService=paymentService; this.passengerRepository=passengerRepository; this.userRepository=userRepository;
    }
    @Override @Transactional(rollbackFor = Exception.class) public Booking bookTicket(BookingRequestDto req){
        Train train = trainRepository.findById(req.getTrainId()).orElseThrow(()->new IllegalArgumentException("Train not found"));
        if(train.getTotalSeats()==null || train.getTotalSeats()<req.getPassengers().size()) throw new IllegalStateException("Not enough seats");
        Booking booking = new Booking(); booking.setPnr(generatePNR()); booking.setTrain(train); booking.setTravelDate(req.getTravelDate()); booking.setSeatsBooked(req.getPassengers().size()); booking.setStatus("PENDING"); booking.setAmount(req.getAmount()); booking.setUser(userRepository.findById(req.getUserId()).orElse(null)); booking = bookingRepository.save(booking);
        Booking finalBooking = booking;
        req.getPassengers().forEach(p->{ Passenger passenger = new Passenger(); passenger.setBooking(finalBooking); passenger.setName(p.getName()); passenger.setAge(p.getAge()); passenger.setGender(p.getGender()); passenger.setStatus("ALLOCATED"); passengerRepository.save(passenger); });
        var pr = paymentService.processPayment(booking.getId(), booking.getAmount(), req.getPaymentDetails()); if(!pr.isSuccess()) throw new RuntimeException("Payment failed: "+pr.getMessage());
        booking.setStatus("CONFIRMED"); bookingRepository.save(booking); return booking;
    }
    @Override public Booking getBookingByPnr(String pnr){ return bookingRepository.findByPnr(pnr).orElseThrow(()->new IllegalArgumentException("PNR not found")); }
    @Override @Transactional public void cancelBooking(String pnr){ Booking booking = bookingRepository.findByPnr(pnr).orElseThrow(()->new IllegalArgumentException("PNR not found")); if("CANCELLED".equalsIgnoreCase(booking.getStatus())) return; booking.setStatus("CANCELLED"); bookingRepository.save(booking); }
    private String generatePNR(){ return "PNR"+UUID.randomUUID().toString().substring(0,10).toUpperCase(); }
}
