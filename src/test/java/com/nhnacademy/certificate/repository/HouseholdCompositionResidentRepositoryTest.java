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
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@WebAppConfiguration
@ContextHierarchy(value = {
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
class HouseholdCompositionResidentRepositoryTest {
    @Autowired
    HouseholdCompositionResidentRepository householdCompositionResidentRepository;

    @Test
    void testFindHouseholdSerialNumber(){
//        assertThat(householdCompositionResidentRepository.findByResident_ResidentSerialNumber(1).getHousehold().getResident().getResidentSerialNumber()).isEqualTo("1");
        assertThat(householdCompositionResidentRepository.findByResident_ResidentSerialNumber(4).getHousehold().getHouseholdCompositionResidents().get(1).getResident().getName()).isEqualTo("이주은");
    }

}