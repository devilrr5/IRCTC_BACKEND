package com.irctc.entity;
import jakarta.persistence.*;
@Entity @Table(name="passenger")
public class Passenger {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne @JoinColumn(name="booking_id") private Booking booking;
    private String name; private Integer age; private String gender; private String seatNo; private String status;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Booking getBooking(){return booking;} public void setBooking(Booking booking){this.booking=booking;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public Integer getAge(){return age;} public void setAge(Integer age){this.age=age;}
    public String getGender(){return gender;} public void setGender(String gender){this.gender=gender;}
    public String getSeatNo(){return seatNo;} public void setSeatNo(String seatNo){this.seatNo=seatNo;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
}
