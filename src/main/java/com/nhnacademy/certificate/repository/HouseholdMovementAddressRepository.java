package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.domain.entitydto.HouseholdMovementAddressDto;
import com.nhnacademy.certificate.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseholdMovementAddressRepository extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.HouseholdMovementAddressPk> {
    HouseholdMovementAddressDto findByHouseholdMovementAddressPk(HouseholdMovementAddress.HouseholdMovementAddressPk householdMovementAddressPk);

    /**
     * 세대주 이사 기록
     */
//    @Query("select hma " +
//            "from Household h inner join fetch h.householdMovementAddresses hma " +
//            "where h.householdSerialNumber= :householdSerialNumber")
//    List<HouseholdMovementAddressDto> findByHouseholdMovementAddressPk_HouseholdSerialNumber(@Param("householdSerialNumber") Integer householdSerialNumber);

    @Query("select hma " +
            "from HouseholdMovementAddress hma " +
            "inner join fetch hma.household h " +
            "where h.householdSerialNumber= :householdSerialNumber")
    List<HouseholdMovementAddressDto> findHouseholdMovementAddresses(@Param("householdSerialNumber") Integer householdSerialNumber);


}
