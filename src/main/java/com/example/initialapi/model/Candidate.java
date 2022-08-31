package com.example.initialapi.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false)
    private int salary;
    private String motivationLetter;
    private String aboutUs;
    @Column(nullable = false)
    private String profile;
    private String post;
}
