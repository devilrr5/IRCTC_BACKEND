package com.irctc.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
@Entity @Table(name="booking")
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(unique=true, nullable=false) private String pnr;
    @ManyToOne @JoinColumn(name="user_id") private User user;
    @ManyToOne @JoinColumn(name="train_id") private Train train;
    @ManyToOne @JoinColumn(name="source_station_id") private Station source;
    @ManyToOne @JoinColumn(name="dest_station_id") private Station dest;
    private LocalDate travelDate; private Integer seatsBooked; private String status; private BigDecimal amount; private LocalDateTime createdAt;
    @PrePersist public void prePersist(){createdAt = LocalDateTime.now();}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getPnr(){return pnr;} public void setPnr(String pnr){this.pnr=pnr;}
    public User getUser(){return user;} public void setUser(User user){this.user=user;}
    public Train getTrain(){return train;} public void setTrain(Train train){this.train=train;}
    public Station getSource(){return source;} public void setSource(Station source){this.source=source;}
    public Station getDest(){return dest;} public void setDest(Station dest){this.dest=dest;}
    public LocalDate getTravelDate(){return travelDate;} public void setTravelDate(LocalDate travelDate){this.travelDate=travelDate;}
    public Integer getSeatsBooked(){return seatsBooked;} public void setSeatsBooked(Integer seatsBooked){this.seatsBooked=seatsBooked;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
    public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal amount){this.amount=amount;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
}
