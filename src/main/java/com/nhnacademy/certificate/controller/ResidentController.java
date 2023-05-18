package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.domain.SimpleResidentDto;
import com.nhnacademy.certificate.service.ResidentService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/residents")
public class ResidentsController {

    private final ResidentService residentService;

    public ResidentsController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping
    public String getResidents(Model model, Pageable pageable) {
        List<SimpleResidentDto> residents = residentService.getResidents(pageable);
        model.addAttribute("residents",residents);
        return "residents";
    }
}
