package com.nhnacademy.certificate.domain.viewdto;

import com.nhnacademy.certificate.domain.restviewdto.ResidentDto;

import java.time.LocalDate;
import java.time.LocalDateTime;


public interface BirthDeathReportResidentDto {
    // 신고일 - 출생사망신고주민 테이블
    LocalDate getBirthDeathReportDate();

    // 출생자 - 성명, 성별, 추생일시, 출생장소, 등록기준지(본적)
    ResidentDto getTargetResident();

    //신고인 - 성명, 주민번호, 자격, 이메일, 전화번호 - 출생사망신고주민 테이블에서 신고자 가져와- param - 출생자, 출생사망유형코드
    ReportResidentRegistrationNumberDto getReportResident();
    String getBirthReportQualificationsCode();
    String getDeathReportQualificationsCode();
    String getEmailAddress();
    String getPhoneNumber();

    interface ReportResidentRegistrationNumberDto{
        String getResidentRegistrationNumber();
        String getName();
    }


}

