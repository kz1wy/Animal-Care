package com.animalcare.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "login_logs")
public class LoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "login_time", nullable = false)
    private LocalDateTime loginTime;

    // constructors, getters and setters
    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }
}
