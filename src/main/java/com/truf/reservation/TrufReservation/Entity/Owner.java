package com.truf.reservation.TrufReservation.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

@Entity
@Table(name = "Owner")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String confirmPassword;

    @Column
    private String phone;

    private String role;

    private String code;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;






}
