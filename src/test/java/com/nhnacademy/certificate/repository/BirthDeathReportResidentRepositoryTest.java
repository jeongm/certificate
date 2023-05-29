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
@ContextHierarchy({
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
class BirthDeathReportResidentRepositoryTest {

    @Autowired
    BirthDeathReportResidentRepository birthDeathReportResidentRepository;

    @Test
    void exitsByReport() {
        assertThat(birthDeathReportResidentRepository.existsByTargetResident_ResidentSerialNumberAndBirthDeathReportResidentPk_BirthDeathTypeCode(1, "사망")).isTrue();
    }

    @Test
    void exitsByReport2() {
        assertThat(birthDeathReportResidentRepository.existsByTargetResident_ResidentSerialNumberAndBirthDeathReportResidentPk_BirthDeathTypeCode(7, "출생")).isTrue();

    }

    @Test
    void test(){
        assertThat(birthDeathReportResidentRepository.findByBirthDeathReportResidentPk_ResidentSerialNumberAndBirthDeathReportResidentPk_BirthDeathTypeCode(7,"출생").getTargetResident().getName()).isEqualTo("남기석");
    }

    @Test
    void test2() {
        assertThat(birthDeathReportResidentRepository.findByBirthDeathReportResidentPk_ResidentSerialNumberAndBirthDeathReportResidentPk_BirthDeathTypeCode(1,"사망").getTargetResident().getName()).isEqualTo("남길동");
    }

}