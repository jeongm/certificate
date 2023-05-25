package com.nhnacademy.certificate.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "household")
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "household_serial_number")
    private Integer householdSerialNumber;
    @Column(name = "household_composition_date")
    private LocalDate householdCompositionDate;
    @Column(name = "household_composition_reason_code")
    private String householdCompositionReasonCode;
    @Column(name = "current_house_movement_address")
    private String currentHouseMovementAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_resident_serial_number")
    private Resident resident;

    @OneToMany(mappedBy = "household")
    private Set<HouseholdMovementAddress> householdMovementAddresses;

    @OneToMany(mappedBy = "household")
    private Set<HouseholdCompositionResident> householdCompositionResidents;

}
