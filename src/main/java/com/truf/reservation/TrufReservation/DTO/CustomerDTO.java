package com.truf.reservation.TrufReservation.DTO;

import lombok.Data;

@Data
public class CustomerDTO {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
    private int user_id;

    public CustomerDTO(int id, String name, String email, String phone, String address, String password, int user_id) {
        this.id=id;
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.address=address;
        this.password=password;
        this.user_id=user_id;
    }
}
