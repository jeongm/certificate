package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.restviewdto.HouseholdMovementAddressDto;
import com.nhnacademy.certificate.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseholdMovementAddressRepository extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.HouseholdMovementAddressPk> {
    HouseholdMovementAddressDto findByHouseholdMovementAddressPk(HouseholdMovementAddress.HouseholdMovementAddressPk householdMovementAddressPk);

    @Query("select hma " +
            "from HouseholdMovementAddress hma " +
            "inner join fetch hma.household h " +
            "where h.householdSerialNumber= :householdSerialNumber")
    List<HouseholdMovementAddressDto> findHouseholdMovementAddresses(@Param("householdSerialNumber") Integer householdSerialNumber);


}
