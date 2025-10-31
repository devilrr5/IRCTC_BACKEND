package com.irctc.entity;
import jakarta.persistence.*;
@Entity @Table(name="train")
public class Train {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(unique=true, nullable=false) private String trainNumber;
    private String name;
    private Integer totalSeats;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getTrainNumber(){return trainNumber;} public void setTrainNumber(String trainNumber){this.trainNumber=trainNumber;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public Integer getTotalSeats(){return totalSeats;} public void setTotalSeats(Integer totalSeats){this.totalSeats=totalSeats;}
}
