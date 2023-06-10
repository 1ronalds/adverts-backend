package com.advert.controller;

import com.advert.model.ApplicationDto;
import com.advert.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;
    
    @GetMapping("/mine/{username}/formine/")
    public ResponseEntity<List<ApplicationDto>> getAllMyApplicationsToMyAdvertismentsCreatedBy(@PathVariable String username){
        return ResponseEntity.ok(applicationService.getAllApplicationsCreatedBy(username));
    }
    @GetMapping("/mine/{username}/forothers/")
    public ResponseEntity<List<ApplicationDto>> getAllApplicationsToOtherAdvertisements(@PathVariable String username){
        return ResponseEntity.ok(applicationService.getAllApplicationsForOthers(username));
    }

    @PostMapping("/mine/{username}/")
    public ResponseEntity<Void> createNewApplication(@PathVariable String username, @RequestBody ApplicationDto applicationDto){
        applicationService.createNewApplication(username, applicationDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/mine/{username}/{application_id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable String username, @PathVariable Long application_id){
        applicationService.deleteApplication(username, application_id);
        return ResponseEntity.noContent().build();
    }

    
}
