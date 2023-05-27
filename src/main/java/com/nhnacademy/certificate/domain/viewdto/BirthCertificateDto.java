package com.nhnacademy.certificate.domain.viewdto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BirthCertificateDto {
    private ResidentNameRegistrationNumberDto mother;
    private ResidentNameRegistrationNumberDto father;
    private BirthDeathReportResidentDto birthReportResident;

}
