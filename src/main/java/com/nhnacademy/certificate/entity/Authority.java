package com.nhnacademy.certificate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Authoroties")
public class Authority {
    @Id
    private String memberId;

    @JoinColumn(name = "authority")
    private String authority;

    @MapsId
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

}