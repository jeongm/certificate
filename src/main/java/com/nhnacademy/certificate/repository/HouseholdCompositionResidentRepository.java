package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdCompositionResidentRepository extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.HouseholdCompositionResidentPk> {
}
