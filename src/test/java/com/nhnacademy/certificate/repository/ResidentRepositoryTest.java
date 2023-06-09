package com.nhnacademy.certificate.repository;

import com.nhnacademy.certificate.config.RootConfig;
import com.nhnacademy.certificate.config.WebConfig;
import com.nhnacademy.certificate.entity.Resident;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Transactional
@WebAppConfiguration
@ContextHierarchy(value = {
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
class ResidentRepositoryTest {

    @Autowired
    private ResidentRepository residentRepository;

    @Test
    void testSave() {
        Resident newResident = Resident.builder()
                .residentSerialNumber(123)
                .name("resident1")
                .residentRegistrationNumber("1111-1111")
                .genderCode("여자")
                .birthDate(LocalDateTime.now())
                .birthPlaceCode("조선대")
                .registrationBaseAddress("광주 동구 조선대")
                .build();
        residentRepository.saveAndFlush(newResident);
        assertThat(residentRepository.existsById(123)).isTrue();
    }

    @Test
    void test2(){
        assertThat(residentRepository.findByFamilyResident(1).get(0).getGenderCode()).isEqualTo("남");
    }


    @Test
    void testFindByMember_Id(){
        assertThat(residentRepository.findByMemberId("admin").getResidentSerialNumber()).isEqualTo(8);
    }


}