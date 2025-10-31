package com.irctc.service.impl; import org.springframework.stereotype.Service; import com.irctc.service.PaymentService; import com.irctc.repository.PaymentRepository; import com.irctc.entity.Payment; import java.math.BigDecimal; import com.irctc.dto.PaymentDetails;
@Service public class MockPaymentService implements com.irctc.service.PaymentService {
    private final PaymentRepository paymentRepository; public MockPaymentService(PaymentRepository paymentRepository){ this.paymentRepository = paymentRepository; }
    @Override public PaymentResult processPayment(Long bookingId, BigDecimal amount, PaymentDetails details){ boolean success = Math.random()>0.15; Payment p = new Payment(); p.setAmount(amount); p.setStatus(success?"SUCCESS":"FAILED"); p.setProviderTxnId("MOCK-"+System.currentTimeMillis()); paymentRepository.save(p); if(success) return new PaymentResult(true,"OK",p.getProviderTxnId()); return new PaymentResult(false,"Mock failure",null); }
}
