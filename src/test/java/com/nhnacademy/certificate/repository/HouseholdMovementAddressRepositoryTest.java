package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.config.RootConfig;
import com.nhnacademy.certificate.config.WebConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@Transactional
@WebAppConfiguration
@ContextHierarchy(value = {
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
class HouseholdMovementAddressRepositoryTest {
    @Autowired
    HouseholdMovementAddressRepository householdMovementAddressRepository;

    @Test
    void findByHouseholdMovementAddressPk_HouseholdSerialNumber() {
//        assertThat(householdMovementAddressRepository.findHouseholdMovementAddresses(5).get(0).getLastAddressYn()).isEqualTo("N");
    }
}