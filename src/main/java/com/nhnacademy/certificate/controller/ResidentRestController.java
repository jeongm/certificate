package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.domain.requestdto.*;
import com.nhnacademy.certificate.exception.ValidationFailedException;
import com.nhnacademy.certificate.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
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
    public ResponseEntity<Void> doRegistryResident(@Valid @RequestBody ResidentRegisterRequest residentRegisterRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }
        residentService.registerResident(residentRegisterRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/residents/{serialNumber}")
    public ResponseEntity<Void> doUpdateResident(@PathVariable("serialNumber") Integer serialNumber,
                                                 @Valid @RequestBody ResidentUpdateRequest residentUpdateRequest,
                                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        residentService.updateResident(serialNumber,residentUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/residents/{serialNumber}/relationship")
    public ResponseEntity<Void> doRegisterFamilyRelationship
            (@PathVariable("serialNumber") Integer serialNumber,
             @Valid @RequestBody FamilyRelationshipRegisterRequest familyRelationshipRegisterRequest,
             BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        familyRelationshipService.registerFamilyRelationship(serialNumber, familyRelationshipRegisterRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/residents/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<Void> doUpdateFamilyRelationship
            (@PathVariable("serialNumber") Integer serialNumber,
             @PathVariable("familySerialNumber") Integer familySerialNumber,
             @Valid @RequestBody FamilyRelationshipUpdateRequest familyRelationshipUpdateRequest,
             BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        familyRelationshipService.updateFamilyRelationship(serialNumber,familySerialNumber,familyRelationshipUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/residents/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<Void> doDeleteFaFamilyRelationship
            (@PathVariable("serialNumber") Integer serialNumber,
             @PathVariable("familySerialNumber") Integer familySerialNumber) {
        familyRelationshipService.deleteFamilyRelationship(serialNumber,familySerialNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/residents/{serialNumber}/birth")
    public ResponseEntity<Void> doRegisterBirthReport(@PathVariable("serialNumber") Integer serialNumber,
                                                      @Valid @RequestBody BirthDeathReportRegisterRequest birthReportRegisterRequest,
                                                      BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        birthDeathReportResidentService.registerBirthReport(serialNumber, birthReportRegisterRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/residents/{serialNumber}/birth/{targetSerialNumber}")
    public ResponseEntity<Void> doUpdateBirthReport(@PathVariable("serialNumber") Integer serialNumber,
                                                    @PathVariable("targetSerialNumber") Integer targetSerialNumber,
                                                    @Valid @RequestBody BirthDeathReportUpdateRequest birthReportUpdateRequest,
                                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        birthDeathReportResidentService.updateBirthReport(serialNumber,targetSerialNumber,birthReportUpdateRequest);
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
                                                      @Valid @RequestBody BirthDeathReportRegisterRequest deathReportRegisterRequest,
                                                      BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        birthDeathReportResidentService.registerDeathReport(serialNumber, deathReportRegisterRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/residents/{serialNumber}/death/{targetSerialNumber}")
    public ResponseEntity<Void> doUpdateDeathReport(@PathVariable("serialNumber") Integer serialNumber,
                                                    @PathVariable("targetSerialNumber") Integer targetSerialNumber,
                                                    @Valid @RequestBody BirthDeathReportUpdateRequest deathReportUpdateRequest,
                                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        birthDeathReportResidentService.updateDeathReport(serialNumber,targetSerialNumber,deathReportUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/residents/{serialNumber}/death/{targetSerialNumber}")
    public ResponseEntity<Void> doDeleteDeathReport(@PathVariable("serialNumber") Integer serialNumber,
                                                    @PathVariable("targetSerialNumber") Integer targetSerialNumber) {

        birthDeathReportResidentService.deleteDeathReport(serialNumber,targetSerialNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/household")
    public ResponseEntity<Void> doRegisterHousehold(@Valid @RequestBody HouseholdRegisterRequest householdRegisterRequest,
                                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

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
                                                                   @Valid @RequestBody HouseholdMovementAddressRegisterRequest householdMovementAddressRegisterRequest,
                                                                   BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        householdMovementAddressService.registerHouseholdMovementAddress(householdSerialNumber,householdMovementAddressRegisterRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/household/{householdSerialNumber}/movement/{reportDate}")
    public ResponseEntity<Void> doUpdateHouseholdMovementAddress(@PathVariable("householdSerialNumber") Integer householdSerialNumber,
                                                                 @PathVariable("reportDate") LocalDate reportDate,
                                                                 @Valid @RequestBody HouseholdMovementAddressUpdateRequest householdMovementAddressUpdateRequest,
                                                                 BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }

        householdMovementAddressService.updateHouseholdMovementAddress(householdSerialNumber, reportDate,householdMovementAddressUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/household/{householdSerialNumber}/movement/{reportDate}")
    public ResponseEntity<Void> doDeleteHouseholdMovementAddress(@PathVariable("householdSerialNumber") Integer householdSerialNumber,
                                                                 @PathVariable("reportDate") LocalDate reportDate){
        householdMovementAddressService.deleteHouseholdMovementAddress(householdSerialNumber,reportDate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
