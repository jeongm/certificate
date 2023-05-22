package com.nhnacademy.certificate.entity;

import javax.persistence.*;

@Table(name = "authorities")
public class Authorities {
    @Id
    private String ResidentSerialNumber;

//    @MapsId
//    @OneToOne
//    @JoinColumn(name = "resident_serial_number")
//    private Resident resident;

    private String authority;
}
