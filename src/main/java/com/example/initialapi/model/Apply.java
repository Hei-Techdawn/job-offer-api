package com.example.initialapi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "apply")
public class Apply implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String motivationLetter;
    @Column(nullable = false)
    private int salary;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "cv_id")
    private Cv cv;
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @PrePersist
    public void prePersist() {
        this.date = new Date();
    }
}
