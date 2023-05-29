package com.nhnacademy.certificate.entity;


import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Members")
public class Member {
    @Id
    @Column(name = "member_id")
    private String id;

//    private String name;

    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @OneToOne(mappedBy = "member", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Authority authority;

}