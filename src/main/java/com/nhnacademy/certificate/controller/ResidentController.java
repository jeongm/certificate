package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.domain.viewdto.CertificateIssueDto;
import com.nhnacademy.certificate.domain.viewdto.*;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ResidentController {
    private final ResidentService residentService;
    private final CertificateIssueService issueService;

    public ResidentController(ResidentService residentService, CertificateIssueService issueService) {
        this.residentService = residentService;
        this.issueService = issueService;
    }


    @GetMapping("/admin/residents")
    public String getResidents(Model model,
                               @PageableDefault(size=5, sort = "residentSerialNumber"
            , direction = Sort.Direction.DESC) Pageable pageable) {
        try{
            int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber()-1);
            pageable = PageRequest.of(page,5);
            Page<ResidentNumberNameReportDto> residents = residentService.getResidents(pageable);
            model.addAttribute("residents",residents);
        }catch (Exception e){
            return "error";
        }

        return "residents";
    }

    @GetMapping("/certificate")
    public String getUserCertificates(HttpServletRequest request,
                                      Model model) {
        try{
            String memberId = (String) request.getSession().getAttribute("username");
            ResidentNumberNameReportDto resident = residentService.getResidentNumberByMemberId(memberId);
            model.addAttribute("user",resident);
        }catch (Exception e){
            return "error";
        }

        return "index";
    }

    @PostMapping("/admin/residents/delete/{serialNumber}")
    public String deleteResident(@PathVariable("serialNumber") Integer residentSerialNumber) {
        try{
            residentService.deleteResident(residentSerialNumber);

        }catch (Exception e){
            return "error";
        }
        return "redirect:/admin/residents";
    }

    @GetMapping("/certificate/family-certificate/{serialNumber}")
    public String getFamilyCertificate(@PathVariable("serialNumber") Integer residentSerialNumber,
                                       Model model){
        try{
            FamilyCertificateDto familyCertificate = issueService.getFamilyCertificate(residentSerialNumber);
            model.addAttribute("familyCertificate",familyCertificate);
        }catch (Exception e){
            return "error";
        }


        return "/certificate/family-certificate";
    }

    @GetMapping("/certificate/resident-certificate/{serialNumber}")
    public String getResidentCertificate(@PathVariable("serialNumber") Integer residentSerialNumber,
                                         Model model){
        try{
            ResidentCertificateDto residentCertificate = issueService.getResidentCertificate(residentSerialNumber);
            model.addAttribute("residentCertificate",residentCertificate);
        }catch (Exception e){
            return "error";
        }
        return "/certificate/resident-certificate";
    }

    @GetMapping("/certificate/birth-certificate/{serialNumber}")
    public String getBirthCertificate(@PathVariable("serialNumber") Integer residentSerialNumber,
                                      Model model){
        try{
            BirthCertificateDto birthCertificate = issueService.getBirthCertificate(residentSerialNumber);
            model.addAttribute("birthCertificate",birthCertificate);
        }catch (Exception e){
            return "error";
        }

        return "/certificate/certificate/birth-certificate";
    }

    @GetMapping("/certificate/death-certificate/{serialNumber}")
    public String getDeathCertificate(@PathVariable("serialNumber") Integer residentSerialNumber,
                                      Model model){
        try{
            BirthDeathReportResidentDto deathCertificate = issueService.getDeathCertificate(residentSerialNumber);
            model.addAttribute("deathCertificate",deathCertificate);
        }catch (Exception e){
            return "error";
        }

        return "/certificate/death-certificate";
    }

    @GetMapping("/certificate/certificate-issue/{serialNumber}")
    public String getCertificateIssueList(@PageableDefault(size=5, sort = "certificateConfirmationNumber", direction = Sort.Direction.DESC)
                                              Pageable pageable,
                                          @PathVariable("serialNumber") Integer residentSerialNumber,
                                          Model model) {

        try{
            int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber()-1);
            pageable = PageRequest.of(page,5);
            Page<CertificateIssueDto> issueList= issueService.getCertificateList(pageable,residentSerialNumber);
            model.addAttribute("issueList", issueList);
            model.addAttribute("resident",residentSerialNumber);
        }catch (Exception e){
            return "error";
        }

        return "certificate/certificate-issue";
    }

}
