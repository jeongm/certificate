package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.domain.entitydto.ResidentSerialNumberAndNameDto;
import com.nhnacademy.certificate.service.ResidentService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/view/residents")
public class ResidentController {
    private final ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping
    public String getResidents(Model model, Pageable pageable) {
        List<ResidentSerialNumberAndNameDto> residents = residentService.getResidents(pageable);
        model.addAttribute("residents",residents);
        return "residents";
    }
}
