package com.irctc.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
@Entity @Table(name="payment")
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne @JoinColumn(name="booking_id") private Booking booking;
    private BigDecimal amount; private String status; private String providerTxnId; private LocalDateTime createdAt;
    @PrePersist public void prePersist(){createdAt = LocalDateTime.now();}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Booking getBooking(){return booking;} public void setBooking(Booking booking){this.booking=booking;}
    public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal amount){this.amount=amount;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
    public String getProviderTxnId(){return providerTxnId;} public void setProviderTxnId(String providerTxnId){this.providerTxnId=providerTxnId;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
}
