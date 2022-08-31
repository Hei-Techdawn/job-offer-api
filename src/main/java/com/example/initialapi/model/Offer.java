package com.example.initialapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Transient
    private String ref;
    private String post;
    private String profile;
    @Column(nullable = false)
    private String place;
    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;
}
