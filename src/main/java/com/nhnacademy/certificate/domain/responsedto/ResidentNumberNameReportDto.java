package com.nhnacademy.certificate.domain.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ResidentNumberNameReportDto {
    private Integer residentSerialNumber;
    private String name;
    private Boolean isBirthReport;
    private Boolean isDeathReport;

}
