package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.domain.entitydto.CertificateIssueDto;
import com.nhnacademy.certificate.domain.responsedto.FamilyCertificateDto;
import com.nhnacademy.certificate.domain.responsedto.ResidentCertificateDto;
import com.nhnacademy.certificate.domain.responsedto.ResidentNumberNameReportDto;
import com.nhnacademy.certificate.service.CertificateIssueService;
import com.nhnacademy.certificate.service.ResidentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class ResidentController {
    private final ResidentService residentService;
    private final CertificateIssueService issueService;

    public ResidentController(ResidentService residentService, CertificateIssueService issueService) {
        this.residentService = residentService;
        this.issueService = issueService;
    }


    @GetMapping("/admin/residents")
    public String getResidents(Model model,@PageableDefault(size=5, sort = "residentSerialNumber", direction = Sort.Direction.DESC) Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber()-1);
        pageable = PageRequest.of(page,5);
        Page<ResidentNumberNameReportDto> residents = residentService.getResidents(pageable);
        model.addAttribute("residents",residents);
        return "residents";
    }

    @PostMapping("/residents/delete/{serialNumber}")
    public String deleteResident(@PathVariable("serialNumber") Integer residentSerialNumber) {
        residentService.deleteResident(residentSerialNumber);
        return "redirect:/admin/residents";
    }

    @GetMapping("/family-certificate/{serialNumber}")
    public String getFamilyCertificate(@PathVariable("serialNumber") Integer residentSerialNumber,
                                       Model model,
                                       @RequestParam(value = "certificateNumber", required = false) Long certificateNumber){
        FamilyCertificateDto familyCertificate = issueService.getFamilyCertificate(residentSerialNumber,certificateNumber);

        model.addAttribute("familyCertificate",familyCertificate);

        return "/certificate/family-certificate";
    }

    @GetMapping("/resident-certificate/{serialNumber}")
    public String getResidentCertificate(@PathVariable("serialNumber") Integer residentSerialNumber,
                                         Model model,
                                         @RequestParam(value = "certificateNumber", required = false) Long certificateNumber){
        ResidentCertificateDto residentCertificate = issueService.getResidentCertificate(residentSerialNumber,certificateNumber);

        return "/certificate/resident-certificate";
    }

    @GetMapping("/birth-certificate/{serialNumber}")
    public String getBirthCertificate(@PathVariable("serialNumber") Integer residentSerialNumber){
        return "/certificate/birth-certificate";
    }

    @GetMapping("/death-certificate/{serialNumber}")
    public String getDeathCertificate(@PathVariable("serialNumber") Integer residentSerialNumber){
        return "/certificate/death-certificate";
    }

    @GetMapping("/certificate-issue/{serialNumber}")
    public String getCertificateIssueList(@PathVariable("serialNumber") Integer residentSerialNumber, Model model) {
        List<CertificateIssueDto> issueList= issueService.getCertificateList(residentSerialNumber);
        model.addAttribute("issueList", issueList);
        model.addAttribute("resident",residentSerialNumber);
        return "certificate/certificate-issue";
    }

}
