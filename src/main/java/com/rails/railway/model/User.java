package com.rails.railway.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    @JsonIgnore
    private Long id;

    @Column(name="username",nullable=false)
    private String username;

    @Column(name="password",nullable=false)
    @JsonIgnore
    private String password;

    @Column(name="email",nullable=false, unique=true)
    private String email;

    @Column(name="phone_number",nullable=true, unique=true)
    private String phoneNumber;

    @Column(name="registration_date")
    private LocalDate registrationDate;

    @Column(name="last_login_date")
    private LocalDate lastLoginDate;

}
