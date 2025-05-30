package com.fitness.classscheduler.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // 🔥 Fix is here
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;
}
