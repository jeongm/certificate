package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.domain.requestdto.*;
import com.nhnacademy.certificate.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping
public class ResidentRestController {

    private final ResidentService residentService;
    private final FamilyRelationshipService familyRelationshipService;
    private final BirthDeathReportResidentService birthDeathReportResidentService;

    public ResidentRestController(ResidentService residentService, FamilyRelationshipService familyRelationshipService, BirthDeathReportResidentService birthDeathReportResidentService, HouseholdService householdService, HouseholdMovementAddressService householdMovementAddressService) {
        this.residentService = residentService;
        this.familyRelationshipService = familyRelationshipService;
        this.birthDeathReportResidentService = birthDeathReportResidentService;
        this.householdService = householdService;
        this.householdMovementAddressService = householdMovementAddressService;
    }

    private final HouseholdService householdService;
    private final HouseholdMovementAddressService  householdMovementAddressService;


    @PostMapping("/residents")
    public ResponseEntity<Void> doRegistryResident(@RequestBody ResidentRegisterRequest residentRegisterRequest) {
        residentService.registerResident(residentRegisterRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/residents/{serialNumber}")
    public ResponseEntity<Void> doUpdateResident(@PathVariable("serialNumber") Integer serialNumber,
                                                 @RequestBody ResidentRegisterRequest residentRegisterRequest) {
        residentService.updateResident(serialNumber,residentRegisterRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/residents/{serialNumber}/relationship")
    public ResponseEntity<Void> doRegisterFamilyRelationship
            (@PathVariable("serialNumber") Integer serialNumber,
             @RequestBody FamilyRelationshipRegisterRequest familyRelationshipRegisterRequest) {
        familyRelationshipService.registerFamilyRelationship(serialNumber, familyRelationshipRegisterRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/residents/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<Void> doUpdateFamilyRelationship
            (@PathVariable("serialNumber") Integer serialNumber,
             @PathVariable("familySerialNumber") Integer familySerialNumber,
             @RequestBody FamilyRelationshipRegisterRequest familyRelationshipRegisterRequest){
        familyRelationshipService.updateFamilyRelationship(serialNumber,familySerialNumber,familyRelationshipRegisterRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/residents/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<Void> doDeleteFaFamilyRelationship
            (@PathVariable("serialNumber") Integer serialNumber,
             @PathVariable("familySerialNumber") Integer familySerialNumber
             ) {
        familyRelationshipService.deleteFamilyRelationship(serialNumber,familySerialNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/residents/{serialNumber}/birth")
    public ResponseEntity<Void> doRegisterBirthReport(@PathVariable("serialNumber") Integer serialNumber,
                                                      @RequestBody BirthDeathReportRegisterRequest birthReportRegisterRequest){
        birthDeathReportResidentService.registerBirthReport(serialNumber, birthReportRegisterRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/residents/{serialNumber}/birth/{targetSerialNumber}")
    public ResponseEntity<Void> doUpdateBirthReport(@PathVariable("serialNumber") Integer serialNumber,
                                                    @PathVariable("targetSerialNumber") Integer targetSerialNumber,
                                                    @RequestBody BirthDeathReportRegisterRequest birthReportRegisterRequest){
        birthDeathReportResidentService.updateBirthReport(serialNumber,targetSerialNumber,birthReportRegisterRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/residents/{serialNumber}/birth/{targetSerialNumber}")
    public ResponseEntity<Void> doDeleteBirthReport(@PathVariable("serialNumber") Integer serialNumber,
                                                    @PathVariable("targetSerialNumber") Integer targetSerialNumber) {
        birthDeathReportResidentService.deleteBirthReport(serialNumber,targetSerialNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/residents/{serialNumber}/death")
    public ResponseEntity<Void> doRegisterDeathReport(@PathVariable("serialNumber") Integer serialNumber,
                                                      @RequestBody BirthDeathReportRegisterRequest deathReportRegisterRequest){
        birthDeathReportResidentService.registerDeathReport(serialNumber, deathReportRegisterRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/residents/{serialNumber}/death/{targetSerialNumber}")
    public ResponseEntity<Void> doUpdateDeathReport(@PathVariable("serialNumber") Integer serialNumber,
                                                    @PathVariable("targetSerialNumber") Integer targetSerialNumber,
                                                    @RequestBody BirthDeathReportRegisterRequest deathReportRegisterRequest){
        birthDeathReportResidentService.updateDeathReport(serialNumber,targetSerialNumber,deathReportRegisterRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/residents/{serialNumber}/death/{targetSerialNumber}")
    public ResponseEntity<Void> doDeleteDeathReport(@PathVariable("serialNumber") Integer serialNumber,
                                                    @PathVariable("targetSerialNumber") Integer targetSerialNumber) {
        birthDeathReportResidentService.deleteDeathReport(serialNumber,targetSerialNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/household")
    public ResponseEntity<Void> doRegisterHousehold(HouseholdRegisterRequest householdRegisterRequest){
        householdService.registerHousehold(householdRegisterRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/household/{householdSerialNumber}")
    public ResponseEntity<Void> doDeleteHousehold(@PathVariable("householdSerialNumber") Integer householdSerialNumber){
        householdService.deleteHousehold(householdSerialNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @PostMapping("/household/{householdSerialNumber}/movement")
    public ResponseEntity<Void> doRegisterHouseholdMovementAddress(@PathVariable("householdSerialNumber") Integer householdSerialNumber,
                                                                   HouseholdMovementAddressRegisterRequest householdMovementAddressRegisterRequest){
        householdMovementAddressService.registerHouseholdMovementAddress(householdSerialNumber,householdMovementAddressRegisterRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/household/{householdSerialNumber}/movement/{reportDate}")
    public ResponseEntity<Void> doUpdateHouseholdMovementAddress(@PathVariable("householdSerialNumber") Integer householdSerialNumber,
                                                                 @PathVariable("reportDate") LocalDate reportDate,
                                                                 HouseholdMovementAddressRegisterRequest householdMovementAddressRegisterRequest){
        householdMovementAddressService.updateHouseholdMovementAddress(householdSerialNumber, reportDate,householdMovementAddressRegisterRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/household/{householdSerialNumber}/movement/{reportDate}")
    public ResponseEntity<Void> doDeleteHouseholdMovementAddress(@PathVariable("householdSerialNumber") Integer householdSerialNumber,
                                                                 @PathVariable("reportDate") LocalDate reportDate){
        householdMovementAddressService.deleteHouseholdMovementAddress(householdSerialNumber,reportDate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
